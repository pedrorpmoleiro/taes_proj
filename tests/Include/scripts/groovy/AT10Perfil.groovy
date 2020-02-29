import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When



class AT10Perfil {
	@Given("I click the Perfil button")
	def i_click_the_Perfil_button() {
		WebUI.click(findTestObject('Object Repository/AT10_Perfil/button_Perfil'))
	}

	@Given("I click on button Alterar Nome on the Perfil page")
	def i_click_on_button_Alterar_Nome_on_the_Perfil_page() {
		WebUI.click(findTestObject('Perfil/button_Alterar Nome'))
	}

	@When("I set the name text to {string}")
	def i_set_the_name_text_to(String name) {

		WebUI.setText(findTestObject('Perfil/input_Name_namePerfil'),name )
	}

	@When("I click on button Alterar on the Perfil page")
	def i_click_on_button_Alterar_on_the_Perfil_page() {

		WebUI.click(findTestObject('Perfil/button_Alterar'))
	}

	@Then("I receive error message {string} in the name field on the Perfil page")
	def i_receive_error_message_in_the_name_field_on_the_Perfil_page(String error) {

		WebUI.verifyElementPresent(findTestObject('Perfil/strong_'+error), 0)
	}

	@Then("I receive success message {string} on the Perfil page")
	def i_receive_success_message_on_the_Perfil_page(String message) {

		WebUI.verifyElementPresent(findTestObject('Perfil/strong_'+message), 0)
	}

	@Then("I click on voltar button")
	def i_click_on_voltar_button() {
		WebUI.click(findTestObject('Perfil/a_Voltar'))
	}

	@Then("I verify the text {string} on the DashBoard")
	def i_verify_the_text_on_the_DashBoard(String texto) {
		WebUI.verifyElementText(findTestObject('DashBoardLoggado/h1_Bem-vindoNome'), texto)
	}
	
	@Given("I click on button Alterar Password on the Perfil page")
	def i_click_on_button_Alterar_Password_on_the_Perfil_page() {
		WebUI.click(findTestObject('Perfil/button_Alterar Password'))

	}
	
	@When("I set the password text to {string}")
	def i_set_the_password_text_to(String password) {
		
WebUI.setText(findTestObject('Perfil/input_New Password_passwordPerfil'), password)

	}
	
	@When("I set the password confirmation text to {string}")
	def i_set_the_password_confirmation_text_to(String passConfirm) {

WebUI.setText(findTestObject('Perfil/input_Password                Confirmation__4e007c'), passConfirm)

	}
	
	@When("I click on button Alterar_Pass on the Perfil page")
	def i_click_on_button_Alterar_Pass_on_the_Perfil_page() {
WebUI.click(findTestObject('Perfil/button_Alterar_Pass'))
}
	@When("I click on button Alterar_Pass on the Perfil page and acceptAlert")
	def i_click_on_button_Alterar_Pass_on_the_Perfil_page_and_acceptAlert() {
WebUI.click(findTestObject('Perfil/button_Alterar_Pass'))
WebUI.acceptAlert()
}
	
	@Then("I receive error message {string} in the password field on the Perfil page")
	def i_receive_error_message_in_the_password_field_on_the_Perfil_page(String message) {
		WebUI.verifyElementPresent(findTestObject('Perfil/strong_'+message), 0)
	}
	
	@When("I click on logout button")
	def i_click_on_logout_button() {

WebUI.click(findTestObject('Object Repository/AT10_Pass/button_Log Out'))
WebUI.acceptAlert()
	}
	
	@Then("I verify the error message {string} on the login page")
	def i_verify_the_error_message_on_the_login_page(String message) {
WebUI.verifyElementPresent(findTestObject('Perfil/div_'+message), 0)
	}
}