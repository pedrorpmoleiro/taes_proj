import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

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


class LandingPageStepDefs {
	@Given("I have the browser open")
	def i_have_the_browser_open() {
		WebUI.openBrowser('')
	}

	@When("I navigate to the {string} web page")
	def i_navigate_to_the_web_page(String page) {
		if(page== "DashBoard"){
			WebUI.navigateToUrl('http://localhost:3000')
		}else{

			WebUI.navigateToUrl('http://localhost:3000/auth/'+page)

			if(page=='login'){
				WebUI.verifyElementText(findTestObject('login Objects/div_login'), 'Login')
			}
			if(page=='register'){
				WebUI.verifyElementText(findTestObject('Register_Objects/div_register'), 'Register')
			}
		}
	}

	@When("I dont fill the {string} input field")
	def i_dont_fill_the_input_field(String input_name) {
		WebUI.setText(findTestObject('login Objects/input_'+input_name), '')
	}


	@When("I dont fill the {string} input field in Register")
	def i_dont_fill_the_input_field_in_register(String input_name) {
		WebUI.setText(findTestObject('Register_Objects/input_Register_'+input_name), '')
	}

	@When("I fill the {string} input field with {string}")
	def i_fill_the_input_field(String input_name,String content ) {
		WebUI.setText(findTestObject('login Objects/input_'+input_name), content)
	}

	@When("I fill the {string} input field with {string} in Register")
	def i_fill_the_input_field_in_register(String input_name,String content ) {
		WebUI.setText(findTestObject('Register_Objects/input_Register_'+input_name), content)
	}

	@When("I click the login button")
	def i_click_the_login_button() {
		WebUI.click(findTestObject('Object Repository/login Objects/button_Login'))
	}

	@When("I click the voltar button on the {string}")
	def i_click_the_voltar_button(String page) {
		if(page=='login'){

			WebUI.click(findTestObject('Object Repository/login Objects/a_Voltar'))
		}else{
			if(page == 'perfil'){
				WebUI.click(findTestObject('Object Repository/Perfil/a_Voltar'))
			}else{
				WebUI.click(findTestObject('Object Repository/Register_Objects/a_Voltar'))
			}
		}
	}

	@When("I click the register button")
	def i_click_the_registar_button() {
		WebUI.click(findTestObject('Object Repository/Register_Objects/button_Register'))
	}

	@Then("I receive error message {string} in the input field")
	def i_receive_error_message_required_in_the_email_input_field(String error) {
		WebUI.verifyElementPresent(findTestObject('Object Repository/login Objects/strong_'+error), 0)
	}

	@Then("I receive server error message {string} global")
	def i_receive_server_error_message_required_in_the_email_input_field(String errorMsg) {
		WebUI.verifyElementPresent(findTestObject('Object Repository/login Objects/div_'+errorMsg), 0)
	}

	@Then("I see the {string} page")
	def i_see_the_page(String page) {
		if(page == 'Dashboard Principal' ){
			WebUI.verifyElementText(findTestObject('DashBoardObjects/h1_Sentinel'), 'Sentinel')
		}
	}

	@Then("I verify the message in the Dashboard")
	def i_verify_the_message_in_the_Dashboard() {
		WebUI.verifyElementPresent(findTestObject('DashBoardObjects/p_MsgError'), 0)

		WebUI.verifyElementPresent(findTestObject('Object Repository/DashBoardObjects/div_airColor'), 0)

		WebUI.verifyElementPresent(findTestObject('Object Repository/DashBoardObjects/p_DataUltimaUpdate'),
				0)
	}

	@When("I click the {string} button on the Dashboard")
	def i_click_the_login_button_on_the_Dashboard(String button) {
		WebUI.click(findTestObject('Object Repository/DashBoardObjects/a_'+button))
	}

	@Then("I verify the presence of {string}")
	def i_verify_the_presence_of(String element) {

		WebUI.verifyElementPresent(findTestObject('DashBoardObjects/div_'+element), 0)
	}

	@Then("I verify the presence of button {string}")
	def i_verify_the_presence_of_button(String button) {
		if(button=="Log Out"){
			WebUI.verifyElementPresent(findTestObject('DashBoardObjects/button_'+button), 0)
		}else{
			WebUI.verifyElementPresent(findTestObject('DashBoardObjects/a_'+button), 0)
		}
	}

	@Then("I verify the ausence of button {string}")
	def i_verify_the_ausence_of_button(String button) {
		if(button=="Log Out"){
			WebUI.verifyElementNotPresent(findTestObject('DashBoardObjects/button_'+button), 0)
		}else{
			WebUI.verifyElementNotPresent(findTestObject('DashBoardObjects/a_'+button), 0)
		}
	}
}