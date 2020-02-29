<template>
  <div>
    <div v-if="!isAutenticado">NOT AUTHORIZED</div>
    <div v-else>
      <Sentinel></Sentinel>
      <nuxt-link to="/" class="btn btn-primary">Voltar</nuxt-link>
      <b-card body-class="text-center" header-bg-variant="warning" align="center">
        <template v-slot:header>
          <h3 class="mb-0">Minha Rota</h3>
        </template>
        <b-card-group>
          <b-card title="Locais">
            <b-form-select id="selectRD" v-model="selectedId" @change="onChange" class="mb-3">
              <option :value="null" disabled>-- Eliga um Lugar --</option>
              <option v-for="option in options" v-bind:value="option.value">{{ option.text }}</option>
            </b-form-select>
          </b-card>
          <b-card border-variant="info">
            <template v-slot:header align="center">
              <h4 class="mb-0">Minhas Aulas</h4>
            </template>
            <b-button id="adicionarRD" v-on:click="adicionar" v-if="adicionarLocal" variant="success">Adicionar
            </b-button>
            <b-button id="removerRD" v-on:click="remover" v-else variant="danger">Remover</b-button>
            <b-table id="listRotaDiaria" ref="selectableTable" selectable select-mode="single" :items="items"
                     :fields="fields"
                     v-if="tableRota"
                     @row-selected="mostrar" responsive="sm">
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
            <b-card-text id="semAulasRD" v-else>
              Não tem aulas para a Rota
            </b-card-text>
          </b-card>
          <b-card id="qualidadeArRD" title="AVG Qualidade do Ar:" body-class="text-center">
            <p id="air">{{this.avgQualidadeAr +"%"}}</p>
            <!--p id="airError" v-else>Impossível obter dados, volte mais tarde</p-->
          </b-card>
        </b-card-group>
      </b-card>
    </div>
  </div>
</template>

<script>
  import Sentinel from "~/components/Sentinel.vue";
  import {auth, StoreDB, FieldValue} from "../plugins/firebase";

  const EDIFICIO_A = "2742611"; // AVEIRO
  const LEIRIA = "2267095";
  const QUITO = "3652462";
  const PONTA_DELGADA = "3372783";
  const ANGRA_DO_HEROISMO = "8010691";
  const LISBOA = "2267057";
  const PORTO = "2735943";
  export default {
    name: "minhaRota",
    components: {
      Sentinel
    },
    data: function () {
      return {
        options: [
          {text: "Global Edifício A", value: EDIFICIO_A},
          {text: "A.S.1.5", value: LEIRIA},
          {text: "A.S.1.4", value: QUITO},
          {text: "A.S.0.1", value: PONTA_DELGADA},
          {text: "A.S.0.3", value: ANGRA_DO_HEROISMO},
          {text: "A.S.0.4", value: LISBOA},
          {text: "A.S.0.5", value: PORTO}
        ],
        tableRota: true,
        fields: ["nome", "qualidadeAr"],
        adicionarLocal: true,
        items: [],
        selectedId: 0,
        avgQualidadeAr: 0,
        qualidadeArSelcted: 0,
        dataWeatherB: null,
        isAutenticado: false
      };
    },
    methods: {
      getItems() {
        let email = localStorage.getItem("user-email");
        if (email == null) {
          email = sessionStorage.getItem("user-email");
        }
        let docRef = StoreDB.collection("userRota");
        let locais = docRef
          .doc(email)
          .get()
          .then(doc => {
            if (!doc.exists) {
              console.log("No such document!");
              this.items = [];
              this.tableRota = false;
            } else {
              this.items = doc.data().rota;
              console.log(this.items);
              this.calculateAvg();
              if (this.items.length == 0) {
                this.tableRota = false;
                this.avgQualidadeAr = 0;
              }
            }
          })
          .catch(err => {
            this.items = [];
            this.tableRota = false;
          });
      },
      adicionar() {
        var nome = this.options.find(e => e.value == this.selectedId).text;
        //PERSISTIR
        let email = localStorage.getItem("user-email");
        //alert(email);
        let docRef = StoreDB.collection("userRota").doc(email);
        console.log("Esto tiene items: " + this.items.length);
        if (this.items.length == 0) {
          docRef
            .set({
              rota: [
                {
                  nome: nome,
                  id: this.selectedId,
                  qualidadeAr: this.qualidadeArSelcted
                }
              ]
            })
            .then(e => {
              this.tableRota = true;
              this.getItems();
              this.adicionarLocal = false;
              // this.calculateAvg();
            });
        } else {
          let setAda = docRef
            .update({
              rota: FieldValue.arrayUnion({
                nome: nome,
                id: this.selectedId,
                qualidadeAr: this.qualidadeArSelcted
              })
            })
            .then(e => {
              this.getItems();
              this.adicionarLocal = false;
              //  this.calculateAvg();
            });
        }
      },
      onChange() {
        if (this.items.length > 0) {
          this.$refs.selectableTable.clearSelected();
        }
        if (!this.items.some(e => e.id == this.selectedId)) {
          this.adicionarLocal = true;
        } else {
          this.adicionarLocal = false;
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
            let temp = (this.dataWeatherB.main.temp - 272.15).toPrecision(3);
            let humidity = this.dataWeatherB.main.humidity;
            this.qualidadeArSelcted = (
              0.8 * temp +
              (humidity / 100) * (temp - 14.3) +
              46.4
            ).toPrecision(3);
          })
          .catch(error => {
            this.servidorEmBaixo = true;
          });
      },
      mostrar(items) {
        if (items[0]) {
          this.adicionarLocal = false;
          this.selectedId = items[0].id;
        }
      },
      remover() {
        if (!confirm("Do you want to really delete?")) {
          //console.log("CANCELED DELETE");
          return;
        }

        // alert(this.selectedId)
        var nome = this.options.find(e => e.value == this.selectedId).text;
        this.qualidadeArSelcted = this.items.find(
          e => e.id == this.selectedId
        ).qualidadeAr;
        let email = localStorage.getItem("user-email");
        let docRef = StoreDB.collection("userRota").doc(email);
        let setAda = docRef
          .update({
            rota: FieldValue.arrayRemove({
              nome: nome,
              id: this.selectedId,
              qualidadeAr: this.qualidadeArSelcted
            })
          })
          .then(e => {
            this.getItems();
            this.adicionarLocal = true;
          });
      },
      calculateAvg() {
        let soma = 0;
        for (let i = 0; i < this.items.length; i++) {
          soma += parseFloat(this.items[i].qualidadeAr);
        }
        console.log(soma);
        if (this.items.length > 0) {
          this.avgQualidadeAr = soma / this.items.length;
        }
        if (this.items.length == 0) {
          this.avgQualidadeAr = 0;
        }
        this.avgQualidadeAr = this.avgQualidadeAr.toPrecision(3);
      }
    },
    mounted() {
      if (auth.currentUser || auth.currentUser != null) {
        this.isAutenticado = true;
      } else {
        this.isAutenticado = false;

        let userEmail = sessionStorage.getItem("user-email");
        let userPass = sessionStorage.getItem("user-pass");

        if (userEmail == null) {
          userEmail = localStorage.getItem("user-email");
          userPass = localStorage.getItem("user-pass");
        }

        if (userEmail == null) {
          this.$router.push({name: "auth-login"});
        }

        auth.signInWithEmailAndPassword(userEmail, userPass).then(() => {
          this.isAutenticado = true;
        });
      }

      this.getItems();
      this.selectedId = EDIFICIO_A;
      this.onChange();
    }
  };
</script>

<style scoped>
  #air {
    font-size: 45px;
    color: #526488;
    word-spacing: 5px;
    text-align: center;
    align-content: center;
    justify-content: center;
    text-shadow: 2px 1px 1px white;
  }

  #airError {
    font-size: 25px;
    color: red;
    word-spacing: 5px;
    padding-bottom: 15px;
    text-shadow: 2px 1px 1px white;
  }
</style>
