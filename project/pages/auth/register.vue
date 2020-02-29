<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header">Register</div>
          <div class="card-body">
            <div class="form-group row">
              <div class="col-md-3 offset-md-0">
                <nuxt-link
                  id="voltarRegister"
                  name="back"
                  to="/"
                  class="btn btn-sm btn-secondary"
                >Voltar</nuxt-link>
              </div>
            </div>
            <form @submit.prevent="register" :disabled="!isFormValid">
              <div
                v-if="registerServerError"
                class="alert alert-danger"
                role="alert"
                id="serverError"
              >{{ registerServerMsg }}</div>

              <div class="form-group row">
                <label class="col-md-6 col-form-label text-md-left">Name:</label>
                <div class="col-md-8">
                  <b-input
                    id="nameRegister"
                    class="form-control"
                    v-model.trim="input.name"
                    :state="isNameValid()"
                    required
                    placeholder="Name"
                  />
                  <span v-if="nameError" class="invalid-feedback" role="alert">
                    <strong id="registerError">{{ nameMsg }}</strong>
                  </span>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-md-6 col-form-label text-md-left">Email:</label>
                <div class="col-md-8">
                  <b-input
                    id="emailRegister"
                    class="form-control"
                    ref="email"
                    type="email"
                    :state="isEmailValid()"
                    required
                    v-model.trim="input.email"
                    placeholder="Email"
                  />
                  <span v-if="emailError" class="invalid-feedback" role="alert">
                    <strong id="registerError">{{ emailMsg }}</strong>
                  </span>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-md-6 col-form-label text-md-left">Password:</label>
                <div class="col-md-8">
                  <b-input
                    id="passwordRegister"
                    class="form-control"
                    type="password"
                    :state="isPasswordValid()"
                    required
                    v-model.trim="input.password"
                    placeholder="Password"
                  />
                  <span v-if="passwordError" class="invalid-feedback" role="alert">
                    <strong id="registerError">{{ passwordMsg }}</strong>
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
                    id="passwordConfirmRegister"
                    class="form-control"
                    type="password"
                    :state="isPasswordConfirmValid()"
                    required
                    v-model.trim="input.passwordConfirmation"
                    placeholder="Password"
                  />
                  <span v-if="passwordConfirmError" class="invalid-feedback" role="alert">
                    <strong id="registerError">{{ passwordConfirmMsg }}</strong>
                  </span>
                </div>
              </div>
              <div class="form-group row">
                <div class="col-md-3 offset-md-0">
                  <button
                    id="registerButton"
                    type="button"
                    class="btn btn-sm btn-primary"
                    @click.prevent="register"
                    :disabled="!isFormValid"
                  >Register</button>
                </div>
              </div>
              <div class="form-group row">
                <label class="col-md-6 col-form-label text-md-right">Já tem conta?</label>
                <nuxt-link to="/auth/login" class="btn btn-sm btn-link">Login</nuxt-link>
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

export default {
  // ahi es login?
  name: "Login",
  data() {
    return {
      nameError: false,
      nameMsg: "",
      emailError: false,
      emailMsg: "",
      passwordError: false,
      passwordMsg: "",
      passwordConfirmError: false,
      passwordConfirmMsg: "",
      registerServerError: false,
      registerServerMsg: "",
      input: {
        name: "",
        email: "",
        password: "",
        passwordConfirmation: ""
      }
    };
  },
  methods: {
    register() {
      if (!this.isFormValid()) {
        return;
      }

      auth
        .createUserWithEmailAndPassword(this.input.email, this.input.password)
        .then(response => {
          let user = response.user;
          user.updateProfile({
            displayName: this.input.name
          });
          //user.sendEmailVerification();

          console.log("USER REGISTERED");

          this.$router.replace({ name: "auth-login" });
        })
        .catch(e => {
          console.log(e);
          this.registerServerError = true;
          this.registerServerMsg = e.message;
          console.log(e.message);
          return;
        });

      auth.signOut();
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
    isEmailValid() {
      if (!this.input.email) {
        this.emailError = true;
        this.emailMsg = "Required";

        return false;
      }

      if (!this.$refs.email.checkValidity()) {
        this.emailError = true;
        this.emailMsg = "Format invalid";

        return false;
      }

      this.emailError = false;
      this.emailMsg = "";

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
      if (!this.isEmailValid()) {
        return false;
      }
      if (!this.isPasswordValid()) {
        return false;
      }
      if (!this.isPasswordConfirmValid()) {
        return false;
      }
      return true;
    }
  }
};
</script>

<style scoped>
</style>
