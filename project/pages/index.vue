<template>
  <div id="page-container">
    <div id="content-wrap">
      <Sentinel></Sentinel>
      <div class="links" v-if="!isUserAutenticado">
        <nuxt-link to="/auth/register" class="button--grey">Register</nuxt-link>
        <nuxt-link to="/auth/login" class="button--green">Login</nuxt-link>
      </div>
      <div class="links" v-else>
        <h1 class="text">Bem-vindo {{user.displayName}}</h1>
        <button type="button" class="btn btn-primary btn-lg" @click.prevent="perfil">Perfil</button>
        <button type="button" class="button--grey" @click.prevent="logOut">Log Out</button>
      </div>
      <div class="super_container">
        <div class="container">
          <b-form-select
            v-model="selectedId"
            @change="onChange"
            class="mb-3"
            v-if="isUserAutenticado"
          >
            <option :value="null" disabled>-- Eliga um Lugar --</option>
            <option v-for="option in options" v-bind:value="option.value">{{ option.text }}</option>
          </b-form-select>
          <div v-if="isUserAutenticado">
            <b-button
              v-on:click="adicionar"
              v-if="adicionarLocal"
              variant="success"
            >Adicionar Locais Favoritos
            </b-button>
            <b-button v-on:click="remover" v-else variant="danger">Remover</b-button>
          </div>
          <div class="mb-2"></div>
          <div>
            <img id="location_img" src/>
          </div>
        </div>
        <div class="container">
          <b-card-group>
            <b-card id="information" :style="getColor">
              <p class="text">Qualidade do Ar:</p>
              <div id="content-air">
                <div class="container">
                  <p id="air" v-if="!servidorEmBaixo">{{this.airPercentagem +"%"}}</p>
                  <p id="airError" v-else>Impossível obter dados, volte mais tarde</p>
                </div>
              </div>
              <b-card
                v-if="!servidorEmBaixo && isUserAutenticado"
                :style="colorTemp"
                class="text-center"
                text-variant="white"
              >
                <b-card-text>Temperatura: {{this.temp + "Cº"}}</b-card-text>
              </b-card>
              <b-card
                v-if="!servidorEmBaixo && isUserAutenticado"
                :style="colorHum"
                class="text-center"
                text-variant="white"
              >
                <b-card-text>Humidade: {{this.humidity + "%"}}</b-card-text>
              </b-card>
              <p id="data">Data última atuliazacao: {{data}}</p>
            </b-card>
            <b-card
              border-variant="info"
              align="center"
              v-if="!servidorEmBaixo&& isUserAutenticado"
            >
              <template v-slot:header>
                <h4 class="mb-0">Locais Favoritos</h4>
              </template>
              <b-table
                ref="selectableTable"
                selectable
                select-mode="single"
                :items="items"
                :fields="fields"
                v-if="locaisFavoritos"
                @row-selected="mostrar"
                responsive="sm"
              >
                <template v-slot:cell(localSelected)="{ rowSelected }">
                  <template v-if="rowSelected">
                    <span aria-hidden="true">&check;</span>
                    <span class="sr-only">Selected</span>
                  </template>
                  <template v-else>
                    <span aria-hidden="true">&nbsp;</span>
                    <span class="sr-only">Not selected</span>
                  </template>
                </template>
              </b-table>
              <b-card-text v-else>Não tem locais favoritos</b-card-text>
            </b-card>
          </b-card-group>
          <b-card
            bg-variant="secondary"
            text-variant="white"
            title="Opções"
            v-if="!servidorEmBaixo&&isUserAutenticado"
          >
            <div class="row">
              <b-button @click="minhaRota" class="btn-warning">Minha Rota</b-button>
              <div class="mr-2"></div>
              <b-button v-on:click="sendData" variant="primary">Enviar Dados</b-button>
              <div class="mr-2"></div>
              <b-button v-on:click="refresh" variant="primary">Refrescar Dados</b-button>
              <div class="mr-2"></div>
              <b-button v-on:click="estatisticas" variant="outline-warning"> Estatisticas</b-button>
              <!-- TWITTER -->
              <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
              <div class="mr-2"></div>
              <div id="twitter-tweet"></div>
              <!-- END -->
            </div>
          </b-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {auth, StoreDB, FieldValue} from "../plugins/firebase";
  import CryptoJS from "crypto-js";
  import Sentinel from "~/components/Sentinel.vue";

  const EDIFICIO_A = "2742611"; // AVEIRO
  const LEIRIA = "2267095";
  const QUITO = "3652462";
  const PONTA_DELGADA = "3372783";
  const ANGRA_DO_HEROISMO = "8010691";
  const LISBOA = "2267057";
  const PORTO = "2735943";


  export default {
    components: {
      Sentinel
    },
    data: function () {
      return {
        twitterMessage: "",
        airPercentagem: 0,
        servidorEmBaixo: false,
        data: null,
        isUserAutenticado: false,
        user: null,
        dataWeatherB: null,
        selectedId: 0,
        colorTemp: {background: "white"},
        colorHum: {background: "white"},
        temp: 0,
        humidity: 0,
        options: [
          {text: "Edifício A", value: EDIFICIO_A, temp: undefined, hum: undefined},
          {text: "A.S.1.5", value: LEIRIA, temp: undefined, hum: undefined},
          {text: "A.S.1.4", value: QUITO, temp: undefined, hum: undefined},
          {text: "A.S.0.1", value: PONTA_DELGADA, temp: undefined, hum: undefined},
          {text: "A.S.0.3", value: ANGRA_DO_HEROISMO, temp: undefined, hum: undefined},
          {text: "A.S.0.4", value: LISBOA, temp: undefined, hum: undefined},
          {text: "A.S.0.5", value: PORTO, temp: undefined, hum: undefined}
        ],
        adicionarLocal: true,
        locaisFavoritos: false,
        items: [],
        fields: ["nome"]
      };
    },
    methods: {
      perfil() {
        if (!auth.currentUser) {
          return;
        }

        this.$router.push({name: "perfil"});
      },
      logOut() {
        if (!auth.currentUser) {
          return;
        }

        if (!confirm("Do you really want to Log Out?")) {
          //console.log("CANCELED LOG OUT");
          return;
        }

        auth.signOut();
        //auth.currentUser = null;
        this.user = null;
        this.isUserAutenticado = false;

        sessionStorage.clear();
        localStorage.clear();

        this.selectedId = EDIFICIO_A;
        this.changeImg();
        this.locaisFavoritos = false;
        console.log("LOGGED OUT");
      },
      getInformation() {
        if (this.selectedId == EDIFICIO_A) {
          // SOMETHING
        }

        this.$axios
          .get(
            "https://api.openweathermap.org/data/2.5/weather?id=" +
            this.selectedId +
            "&APPID=57202eeb6c388e6546d782289d3a76fc"
          )
          .then(response => {
            // console.log(response);
            this.dataWeatherB = response.data;
            this.setInformation();
            if (this.isUserAutenticado) this.twitter();
          })
          .catch(error => {
            this.servidorEmBaixo = true;
            console.log(error);
          });
      },
      onChange() {
        if (this.items.length > 0) {
          this.$refs.selectableTable.clearSelected();
        }
        this.changeImg();
      },
      changeImg() {
        let photo = document.getElementById("location_img");
        if (!this.items.some(e => e.id == this.selectedId)) {
          this.adicionarLocal = true;
        } else {
          this.adicionarLocal = false;
        }
        switch (this.selectedId) {
          case EDIFICIO_A:
            photo.src = "edificioA.jpg";
            break;
          case LEIRIA:
            photo.src = "a.s.1.5.jpeg";
            break;
          case QUITO:
            photo.src = "a.s.1.4.jpeg";
            break;
          case PONTA_DELGADA:
            photo.src = "a.s.0.1.jpeg";
            break;
          case ANGRA_DO_HEROISMO:
            photo.src = "a.s.0.3.jpeg";
            break;
          case LISBOA:
            photo.src = "a.s.0.4.jpeg";
            break;
          case PORTO:
            photo.src = "a.s.0.5.jpeg";
            break;
          default:
            photo.src = "";
        }
        this.getInformation();
      },
      setInformation() {
        this.temp = (this.dataWeatherB.main.temp - 272.15).toPrecision(3);
        this.humidity = this.dataWeatherB.main.humidity;
        this.airPercentagem = (
          0.8 * this.temp +
          (this.humidity / 100) * (this.temp - 14.3) +
          46.4
        ).toPrecision(3);
        this.data = Date();
        if (this.temp < 19 || this.temp > 35) {
          this.colorTemp = {background: "red"};
        } else {
          if (this.temp > 19 || this.temp < 35) {
            this.colorTemp = {background: "green"};
          }
        }
        if (this.humidity < 50 || this.humidity > 75) {
          this.colorHum = {background: "red"};
        } else {
          if (this.humidity < 75 || this.humidity > 50) {
            this.colorHum = {background: "green"};
          }
        }
      },
      adicionar() {
        var nome = this.options.find(e => e.value == this.selectedId).text;
        //PERSISTIR
        let email = localStorage.getItem("user-email");
        let docRef = StoreDB.collection("favoritos").doc(email);
        //console.log("Esto tiene items: "+this.items.length)
        if (this.items.length == 0) {
          docRef.set({locais: [{nome: nome, id: this.selectedId}]});
          this.locaisFavoritos = true;
        } else {
          let setAda = docRef
            .update({
              locais: FieldValue.arrayUnion({nome: nome, id: this.selectedId})
            })
            .then(e => {
              this.getItems();
            });
        }
        this.getItems();
        this.adicionarLocal = false;
      },
      remover() {
        if (!confirm("Do you want to really delete?")) {
          //console.log("CANCELED DELETE");
          return;
        }

        var nome = this.options.find(e => e.value == this.selectedId).text;
        let email = localStorage.getItem("user-email");
        //console.log("a remover" + this.selectedId)
        let docRef = StoreDB.collection("favoritos").doc(email);
        let setAda = docRef
          .update({
            locais: FieldValue.arrayRemove({nome: nome, id: this.selectedId})
          })
          .then(e => {
            this.getItems();
          });
        this.adicionarLocal = true;
      },
      mostrar(items) {
        if (items[0]) {
          this.adicionarLocal = false;
          this.selectedId = items[0].id;
          this.changeImg();
        }
      },
      getItems() {
        let email = localStorage.getItem("user-email");
        let docRef = StoreDB.collection("favoritos");
        let locais = docRef
          .doc(email)
          .get()
          .then(doc => {
            if (!doc.exists) {
              //console.log('No such document!');
              this.items = [];
              this.locaisFavoritos = false;
            } else {
              this.items = doc.data().locais;
              //console.log(this.items)
              if (this.items.length == 0) {
                this.selectedId = EDIFICIO_A;
                this.changeImg();
                this.locaisFavoritos = false;
              }
            }
          })
          .catch(err => {
            this.items = [];
            this.locaisFavoritos = false;
          });

        console.log(locais);
      },
      minhaRota() {
        this.$router.push({name: "minhaRota"});
      },
      twitter() {
        this.twitterMessage =
          "Air Quality @ " +
          this.options.find(e => e.value == this.selectedId).text +
          " is " +
          this.airPercentagem +
          "%";

        document.getElementById("twitter-tweet").innerHTML = "";

        twttr.widgets.createShareButton(
          "/",
          document.getElementById("twitter-tweet"),
          {
            text: this.twitterMessage,
            size: "large",
            hashtags: "SENTINEL"
          }
        );
      },
      sendData() {
        this.$router.push({name: "sendData"});
      },
      estatisticas() {
        this.$router.push({name: 'statistics'})
      },
      saveData() {
        //PERSISTIR
        let docRef = StoreDB.collection("estadisticas").doc('lastWeek');
        let setAda = docRef.set({'time': this.options})
          .then(r => {
            console.log('Dados persistidos')
          })
          .catch(e => {
            console.log(e)
          })
      },
      fillArray() {
        var i = 0;
        this.options.forEach(async e => {
          try {
            let r = await this.$axios.get("https://api.openweathermap.org/data/2.5/weather?id=" + e.value + "&APPID=57202eeb6c388e6546d782289d3a76fc");
            this.options.find(e => e.value == r.data.id).temp = (r.data.main.temp - 272.15).toPrecision(3);
            this.options.find(e => e.value == r.data.id).hum = r.data.main.humidity//console.log(this.options)
            i++;
            if (i == this.options.length) {
              this.saveData();
            }
          } catch (e) {
            console.log(e)
          }

        });

      },
      refresh() {
        this.getInformation();
      }
    },
    computed: {
      getColor() {
        if (this.servidorEmBaixo) {
          return {background: "white"};
        }
        if (
          this.colorTemp.background == "red" &&
          this.colorHum.background == "red"
        ) {
          return {background: "red"};
        } else if (
          (this.colorTemp.background == "green" &&
            this.colorHum.background == "red") ||
          (this.colorTemp.background == "red" &&
            this.colorHum.background == "green")
        ) {
          return {background: "yellow"};
        } else if (
          this.colorTemp.background == "green" &&
          this.colorHum.background == "green"
        ) {
          return {background: "green"};
        }
      }
    },
    mounted() {
      //console.log(process.env.encKey);

      if (auth.currentUser || auth.currentUser != null) {
        this.isUserAutenticado = true;
        this.locaisFavoritos = true;
        this.user = auth.currentUser;
        this.selectedId = EDIFICIO_A;
        this.changeImg();
      } else {
        let userEmail = sessionStorage.getItem("user-email");
        let userPass = sessionStorage.getItem("user-pass");

        if (userEmail == null) {
          userEmail = localStorage.getItem("user-email");
          userPass = localStorage.getItem("user-pass");
          this.locaisFavoritos = true;
        }

        if (userEmail == null) {
          // Nothing stored

          this.selectedId = EDIFICIO_A;
          this.changeImg();

          return;
        } else {
          this.selectedId = EDIFICIO_A;
          this.changeImg();
        }

        //userEmail = CryptoJS.AES.decrypt(userEmail.toString(), process.env.encKey).toString();
        //userPass = CryptoJS.AES.decrypt(userPass.toString(), process.env.encKey).toString();

        //console.log("Email: " + userEmail);
        //console.log("Pass: " + userPass);

        auth.signInWithEmailAndPassword(userEmail, userPass).then(() => {
          this.isUserAutenticado = true;
          this.user = auth.currentUser;
        });
      }
      this.getItems();
      //    this.fillArray();
      // this.saveData();
    }
  };
</script>

<style>
  body {
    margin: 0;
  }

  /* TODO LO QUE TIENE QUE VER CON TEXTO BOTONES Y POSICIONAMINETO*/
  #page-container {
    position: relative;
    min-height: 100vh;
  }

  #footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 5rem; /* Footer height */
    justify-content: center;
    align-items: center;
    text-align: center;
    background: #35495e;
  }

  #content-wrap {
    padding-bottom: 2.5rem; /* Footer height */
  }

  .header {
    top: 0;
    width: 100%;
    height: 60px;
    border-bottom: 1px solid paleturquoise;
    display: flex;
    justify-content: center;
    text-align: center;
    padding: 0px 0px 0px 0px;
    background: #35495e;
  }

  .title {
    font-family: "Quicksand", "Source Sans Pro", -apple-system, BlinkMacSystemFont,
    "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
    display: inline;
    font-weight: 200;
    font-size: 50px;
    color: white;
    letter-spacing: 1px;
    justify-self: center;
  }

  .subtitle {
    font-weight: 300;
    font-size: 20px;
    color: white;
    word-spacing: 5px;
    padding-bottom: 15px;
  }

  .super_container {
    margin: 25px 100px auto;
    justify-self: center;
    align-self: center;
  }

  .container {
    justify-content: center;
    align-items: center;
    width: 50%;
    height: auto;
    float: left;
  }

  .text {
    font-size: 30px;
    color: black;
    word-spacing: 5px;
    border-bottom: 1px solid white;
  }

  .links {
    padding-top: 25px;
    text-align: right;
  }

  /*--------------------------------------------------*/

  /*---------------------TODO CON AIRE-------------*/
  #information {
    background-color: paleturquoise;
    border-radius: 5px;
  }

  #air {
    font-size: 40px;
    color: black;
    word-spacing: 5px;
    padding-bottom: 15px;
  }

  #airError {
    font-size: 25px;
    color: red;
    word-spacing: 5px;
    padding-bottom: 15px;
    text-shadow: 2px 1px 1px white;
  }

  #content-air {
    justify-content: center;
    text-align: center;
    align-items: center;
  }

  #data {
    font-size: 15px;
    color: black;
    word-spacing: 5px;
    padding-bottom: 15px;
  }

  img {
    width: 100%;
    height: 75%;
    border-radius: 5px;
  }

  .airColor {
    margin-top: 15px;
    margin-left: 25px;
    width: 25px;
    height: 25px;
    border: 1px solid black;
    float: left;
  }
</style>
