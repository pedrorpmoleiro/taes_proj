<template>
  <div>
    <Sentinel></Sentinel>
    <div id="container">
      <b-card bg-variant="light" title="Enviar Dados">
        <nuxt-link to="/" class="btn  btn-primary">Voltar</nuxt-link>
        <b-form-group>
          <b-input-group prepend="Locais" class="mb-2">
            <b-form-select v-model="selectedId" @change="onChange" id="locais" :disabled="disabled == 1">
              <option :value="null" disabled>-- Eliga um Lugar --</option>
              <option v-for="option in options" v-bind:value="option.value">
                {{ option.text }}
              </option>
            </b-form-select>
            <b-input-group-append>
              <b-button @click="minha" variant="outline-secondary">Minha Localização</b-button>
            </b-input-group-append>
          </b-input-group>
        </b-form-group>
        <b-card title="Dados dos Sensores" body-class="text-center">
          <b-row>
            <b-col>
              <b-form-group label="Temperatura:">
                <b-form-input size="lg" id="nested-street" readonly v-model="temp"></b-form-input>
              </b-form-group>
            </b-col>
            <b-col>
              <b-form-group label="Humidade:">
                <b-form-input size="lg" id="nested-street" readonly v-model="hum"></b-form-input>
              </b-form-group>
            </b-col>
          </b-row>
          <b-button type="submit" @click.prevent="submit" variant="primary">Submit</b-button>
        </b-card>
      </b-card>
    </div>
  </div>
</template>

<script>
  import Sentinel from "~/components/Sentinel.vue";

  const EDIFICIO_A = "2742611"; // AVEIRO
  const LEIRIA = "2267095";
  const QUITO = "3652462";
  const PONTA_DELGADA = "3372783";
  const ANGRA_DO_HEROISMO = "8010691";
  const LISBOA = "2267057";
  const PORTO = "2735943";
  export default {
    name: "sendData",
    components: {
      Sentinel
    },
    data: function () {
      return {
        selectedId: 0,
        options: [
          {text: "Global Edifício A", value: EDIFICIO_A},
          {text: "A.S.1.5", value: LEIRIA},
          {text: "A.S.1.4", value: QUITO},
          {text: "A.S.0.1", value: PONTA_DELGADA},
          {text: "A.S.0.3", value: ANGRA_DO_HEROISMO},
          {text: "A.S.0.4", value: LISBOA},
          {text: "A.S.0.5", value: PORTO}
        ],
        temp: 0,
        hum: 0,
        disabled: 0,
      }
    }, mounted() {
      this.selectedId = EDIFICIO_A;
      this.onChange();
    },
    methods: {
      onChange() {
        this.temp = (Math.random() * (27 - 11) + 11).toPrecision(4);
        this.hum = (Math.random() * (88 - 33) + 33).toPrecision(4);
      },
      minha() {
        this.disabled = (this.disabled + 1) % 2
        let rand = this.getRandomInt(0, this.options.length - 1);
        // console.log(this.options[rand].value);
        this.selectedId = this.options[rand].value;
        this.onChange();
      },
      getRandomInt(min, max) {
        min = Math.ceil(min);
        max = Math.floor(max);
        return Math.floor(Math.random() * (max - min + 1)) + min;
      },
      submit() {
        var data = JSON.stringify({
          temperature: this.temp,
          humidity: this.hum
        })
        this.$axios.post({method: 'post', url: '#', data: data, headers: {'Content-Type': 'application/json'}})
          .then(r => {
            alert("SUCCESS");
            this.$router.push({name: "index"})
          })
          .catch(e => {
            alert("ERROR");
            this.$router.push({name: "index"})
          })
      }
    }
  }
</script>

<style scoped>
  #container {
    padding-left: 15%;
    padding-right: 15%;
    padding-top: 5%;
  }
</style>
