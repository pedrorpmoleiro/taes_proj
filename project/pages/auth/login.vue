<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header">Login</div>
          <div class="card-body">
            <div class="form-group row">
              <div class="col-md-3 offset-md-0">
                <nuxt-link id="voltarLogin" to="/" class="btn btn-sm btn-primary">Voltar</nuxt-link>
              </div>
            </div>
            <form @submit.prevent="login" :disabled="!isFormValid">
              <div
                v-if="loginError"
                class="alert alert-danger"
                role="alert"
                id="loginError"
              >{{ loginMsg }}</div>

              <div class="form-group row">
                <label class="col-md-6 col-form-label text-md-left">Email:</label>
                <div class="col-md-8">
                  <b-input
                    class="form-control"
                    type="email"
                    ref="email"
                    id="emailLogin"
                    v-model.trim="input.email"
                    required
                    :state="isEmailValid()"
                    placeholder="Email"
                  />
                  <span v-if="loginError" class="invalid-feedback" role="alert">
                    <strong>{{ loginMsg }}</strong>
                  </span>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-md-6 col-form-label text-md-left">Password:</label>
                <div class="col-md-8">
                  <b-input
                    class="form-control"
                    type="password"
                    id="passwordLogin"
                    v-model.trim="input.password"
                    :state="isPasswordValid()"
                    required
                    placeholder="Password"
                  />
                  <span v-if="loginError" class="invalid-feedback" role="alert">
                    <strong>{{ loginMsg }}</strong>
                  </span>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-md-8">
                  <label for="manterConta">Manter a conta aberta</label>
                  <input type="checkbox" id="manterConta" v-model="input.checked" />
                </div>
              </div>
              <div class="form-group row">
                <div class="col-md-8">
                  <button
                    type="button"
                    id="buttonLogin"
                    class="btn btn-sm btn-primary"
                    @click.prevent="submit"
                    :disabled="!isFormValid"
                  >Login</button>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-md-8">
                  <nuxt-link to="/" class="btn btn-sm btn-link">Esqueceste-te da tua conta?</nuxt-link>
                </div>
              </div>
              <div class="form-group row">
                <label>Ainda não tem conta:</label>
                <div class="col-md-8">
                  <nuxt-link to="/auth/register" class="btn btn-sm btn-link">Registar</nuxt-link>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { auth } from "../../plugins/firebase";
import CryptoJS from "crypto-js";

export default {
  name: "Login",
  data() {
    return {
      loginError: false,
      loginMsg: "",
      input: {
        email: "",
        password: "",
        checked: true
      }
    };
  },
  methods: {
    submit() {
      if (!this.isFormValid()) {
        return;
      }

      auth
        .signInWithEmailAndPassword(this.input.email, this.input.password)
        .then(() => {
          this.$emit("authenticated", true);
          //this.$router.replace({name: "secure"});

          this.loginError = false;
          this.loginMsg = "";

          console.log("LOGGED IN");

          if (!this.input.checked) {
            let userEmail = this.input.email;
            //let userEmail = CryptoJS.AES.encrypt(this.input.email, process.env.encKey);
            sessionStorage.setItem("user-email", userEmail);
            let userPass = this.input.password;
            //let userPass = CryptoJS.AES.encrypt(this.input.password, process.env.encKey);
            sessionStorage.setItem("user-pass", userPass);
          } else {
            let userEmail = this.input.email;
            //let userEmail = CryptoJS.AES.encrypt(this.input.email, process.env.encKey);
            localStorage.setItem("user-email", userEmail);
            let userPass = this.input.password;
            //let userPass = CryptoJS.AES.encrypt(this.input.password, process.env.encKey);
            localStorage.setItem("user-pass", userPass);
          }

          this.$router.push({ name: "index" });
        })
        .catch(e => {
          console.log(e);

          this.loginError = true;
          this.loginMsg = e.message;

          return;
        });

      if (this.input.checked) {
      }
    },
    isEmailValid() {
      if (!this.input.email) {
        this.loginError = true;
        this.loginMsg = "Required";
        return false;
      }

      return this.$refs.email.checkValidity();
    },
    isPasswordValid() {
      if (!this.input.password) {
        this.loginError = true;
        this.loginMsg = "Required";
        return false;
      }

      let passwordLen = this.input.password.length;
      if (passwordLen < 6 || passwordLen > 255) {
        return false;
      }
      return true;
    },
    isFormValid() {
      if (!this.isEmailValid) {
        return false;
      }
      if (!this.isPasswordValid) {
        return false;
      }
      return true;
    }
  }
};
</script>

<style scoped>
</style>
