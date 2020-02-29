<template>
  <div>
    <Sentinel></Sentinel>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-12">
          <div class="form-group row">
            <div class="col-md-3 offset-md-0">
              <nuxt-link id="voltarPerfil" name="back" to="/" class="btn btn-sm btn-secondary">Voltar</nuxt-link>
            </div>
          </div>
          <div class="card" v-if="!showAlterarPass">
            <div class="card-header">Perfil</div>
            <div class="card-body">
              <form @submit.prevent="AlterarNome" :disabled="!isFormValid">
                <!-- <div
                  v-if="updateServerError"
                  class="alert alert-danger"
                  role="alert"
                >{{ updateServerMsg }}</div>-->

                <div class="alert" :class="typeofmsg" v-if="showMessage">
                  <button type="button" class="close-btn" v-on:click="showMessage = false">&times;</button>
                  <strong>{{ alertMessage }}</strong>
                </div>

                <div class="form-group row">
                  <label class="col-md-6 col-form-label text-md-left">Name:</label>
                  <div class="col-md-8">
                    <b-input
                            id="namePerfil"
                            class="form-control"
                            v-model.trim="input.name"
                            :state="isNameValid()"
                            :readonly="readOnlyPerfil"
                            required
                            placeholder="Name"
                    />
                    <span v-if="nameError && !readOnlyPerfil" class="invalid-feedback" role="alert">
                    <strong>{{ nameMsg }}</strong>
                  </span>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-md-6 col-form-label text-md-left">Email:</label>
                  <div class="col-md-8">
                    <b-input
                            id="emailPerfil"
                            class="form-control"
                            ref="email"
                            type="email"
                            v-model.trim="input.email"
                            :readonly="true"
                            placeholder="Email"
                    />
                  </div>
                </div>
                <b-card bg-variant="white" text-variant="black" title="Opções" v-if="opcoes">
                  <div class="form-group row">
                    <div class="col-md-2">
                      <button
                              id="OpcaoAlterarNomeButton"
                              type="button"
                              class="btn btn-sm btn-primary"
                              @click.prevent="OpcaoAlterarNome"
                      >Alterar Nome</button>
                    </div>
                    <div class="col-md-2">
                      <button
                              id="OpcaoAlterarPasswordButton"
                              type="button"
                              class="btn btn-sm btn-primary"
                              @click.prevent="OpcaoAlterarPassword"
                      >Alterar Password</button>
                    </div>
                  </div>
                </b-card>
                <div class="form-group row">
                  <div class="col-md-3 offset-md-0">
                    <button
                            id="AlterarNomeButton"
                            type="button"
                            class="btn btn-sm btn-primary"
                            v-if="showAlterarNome"
                            @click.prevent="AlterarNome"
                    >Alterar</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
          <div class="card" v-if="showAlterarPass">
            <div class="card-header">Alterar Password</div>
            <div class="card-body">
              <form @submit.prevent="updatePassword" :disabled="!isFormValidPass">
                <div class="alert" :class="typeofmsg" v-if="showMessage">
                  <button type="button" class="close-btn" v-on:click="showMessage = false">&times;</button>
                  <strong>{{ alertMessage }}</strong>
                </div>

                <div class="form-group row">
                  <label class="col-md-6 col-form-label text-md-left">New Password:</label>
                  <div class="col-md-8">
                    <b-input
                            id="passwordPerfil"
                            class="form-control"
                            type="password"
                            :state="isPasswordValid()"
                            required
                            v-model.trim="input.password"
                            placeholder="Password"
                    />
                    <span v-if="passwordError" class="invalid-feedback" role="alert">
                    <strong>{{ passwordMsg }}</strong>
                  </span>
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-md-6 col-form-label text-md-left">
                    Password
                    Confirmation:
                  </label>
                  <div class="col-md-8">
                    <b-input
                            id="passwordConfirmPerfil"
                            class="form-control"
                            type="password"
                            :state="isPasswordConfirmValid()"
                            required
                            v-model.trim="input.passwordConfirmation"
                            placeholder="Password"
                    />
                    <span v-if="passwordConfirmError" class="invalid-feedback" role="alert">
                    <strong>{{ passwordConfirmMsg }}</strong>
                  </span>
                  </div>
                </div>
                <div class="form-group row">
                  <div class="col-md-3 offset-md-0">
                    <button
                            id="AlterarPasswordButton"
                            type="button"
                            class="btn btn-sm btn-primary"
                            v-if="showAlterarPass"
                            @click.prevent="updatePassword"
                    >Alterar</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { auth, StoreDB, FieldValue } from "../plugins/firebase";
import Sentinel from "~/components/Sentinel.vue";
export default {
  components:{Sentinel},
  data() {
    return {
      isUserAutenticado: false,
      user: null,
      nameError: false,
      nameMsg: "",
      passwordError: false,
      passwordMsg: "",
      passwordConfirmError: false,
      passwordConfirmMsg: "",
      showMessage: false,
      alertMessage: "",
      typeofmsg: "alert-success",
      readOnlyPerfil: true,
      showAlterarPass: false,
      opcoes: true,
      showAlterarNome: false,
      showAlterarPass: false,
      input: {
        name: "",
        email: ""
        //password: "",
        //passwordConfirmation: ""
      }
    };
  },
  methods: {
    OpcaoAlterarPassword() {
      this.showAlterarPass = true;
      this.opcoes = false;
      this.showAlterarPass = true;
    },
    OpcaoAlterarNome() {
      this.showAlterarNome = true;
      this.opcoes = false;
      this.readOnlyPerfil = false;
    },
     AlterarNome() {
         console.log(this.isFormValid())
      if (!this.isFormValid()) {
        return;
      }
      if (this.user == null) {
        return;
      }
       this.user
        .updateProfile({
          displayName: this.input.name
        })
        .then(response => {
          console.log("NAME UPDATE");
          this.showMessage = true;
          this.alertMessage = "Name Update successfully";
          this.typeofmsg = "alert-success";
        })
        .catch(error => {
          console.log(error);
          this.showMessage = true;
          this.alertMessage = error.message;
          this.typeofmsg = "alert-danger";
          console.log(error.message);
          return;
        });
    },
     updatePassword() {
      if (!this.isFormValidPass()) {
        return;
      }
      if (this.user == null) {
        return;
      }
       if (!confirm("Do you really want to change the password?")) {
          //console.log("CANCELED Update pass");
          return;
        }
      var newPassword = this.input.password;

       this.user
        .updatePassword(newPassword)
        .then(response => {
          console.log("PASSWORD UPDATE");
          this.showMessage = true;
          this.alertMessage = "Password Update successfully";
          this.typeofmsg = "alert-success";
        })
        .catch(error => {
          console.log(error);
          this.showMessage = true;
          this.alertMessage = error.message;
          this.typeofmsg = "alert-danger";
          console.log(error.message);
          return;
        });
    },
    isNameValid() {
      if (!this.input.name) {
        this.nameError = true;
        this.nameMsg = "Required";

        return false;
      }

      let nameLen = this.input.name.length;
      if (nameLen < 3) {
        this.nameError = true;
        this.nameMsg = "Too short (min 3 characters)";

        return false;
      } else {
        if (nameLen > 50) {
          this.nameError = true;
          this.nameMsg = "Too long (max 50 characters)";

          return false;
        }
      }

      this.nameError = false;
      this.nameMsg = "";

      return true;
    },
    isPasswordValid() {
      if (!this.input.password) {
        this.passwordError = true;
        this.passwordMsg = "Required";

        return false;
      }

      let passwordLen = this.input.password.length;
      if (passwordLen < 6) {
        this.passwordError = true;
        this.passwordMsg = "Too short (min 6 characters)";

        return false;
      } else {
        if (passwordLen > 255) {
          this.passwordError = true;
          this.passwordMsg = "Too long (max 255 characters)";

          return false;
        }
      }

      this.passwordError = false;
      this.passwordMsg = "";

      return true;
    },
    isPasswordConfirmValid() {
      if (!this.input.passwordConfirmation) {
        this.passwordConfirmError = true;
        this.passwordConfirmMsg = "Required";

        return false;
      }

      if (this.input.passwordConfirmation != this.input.password) {
        this.passwordConfirmError = true;
        this.passwordConfirmMsg = "Passwords don't match";

        return false;
      }

      this.passwordConfirmError = false;
      this.passwordConfirmMsg = "";

      return true;
    },
    isFormValid() {
      if (!this.isNameValid()) {
        return false;
      }
      return true;
    },
    isFormValidPass() {
      if (!this.isPasswordValid()) {
        return false;
      }
      if (!this.isPasswordConfirmValid()) {
        return false;
      }
      return true;
    }
  },
  mounted() {
    if (auth.currentUser || auth.currentUser != null) {
      this.isAutenticado = true;
      this.user = auth.currentUser;
      //console.log(this.user);
      this.input.email = this.user.email;
      this.input.name = this.user.displayName;
    } else {
      this.isAutenticado = false;

      let userEmail = sessionStorage.getItem("user-email");
      let userPass = sessionStorage.getItem("user-pass");

      if (userEmail == null) {
        userEmail = localStorage.getItem("user-email");
        userPass = localStorage.getItem("user-pass");
      }

      if (userEmail == null) {
        this.$router.push({ name: "auth-login" });
      }
      //console.log(this.user);
      auth.signInWithEmailAndPassword(userEmail, userPass).then(() => {
        this.isAutenticado = true;
        this.user = auth.currentUser;
        //console.log(this.user);
        this.input.email = this.user.email;
        this.input.name = this.user.displayName;
      });
    }
  }
};
</script>
