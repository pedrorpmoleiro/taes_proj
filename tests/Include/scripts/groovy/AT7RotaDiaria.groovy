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



class AT7RotaDiaria {
	@Given("I click the RotaDiaria button")
	def i_click_the_RotaDiaria_button() {
		WebUI.click(findTestObject('DashBoardLoggado/button_Minha Rota'))
	}
	@When("I select option with value {string} on the RotaDiaria")
	def i_select_option_with_value_on_the_RotaDiaria(String localvalue) {
		WebUI.selectOptionByValue(findTestObject('RotaDiaria/select_RotaDiaria'), localvalue, false)
	}
/*
	@When("I select option {string} on the RotaDiaria")
	def i_select_option_on_the_RotaDiaria(String local) {
		WebUI.selectOptionByLabel(findTestObject('RotaDiaria/select_RotaDiaria'), local, false)
	}*/
	
	@When("I click on button Adicionar on the RotaDiaria")
	def i_click_on_button_Adicionar_on_the_RotaDiaria() {
		WebUI.click(findTestObject('RotaDiaria/button_Adicionar'))
	}

	@Then("I verify the presence of {string} on the list of RotaDiaria")
	def i_verify_the_presence_of_on_the_list_of_RotaDiaria(String local) {
		WebUI.verifyElementPresent(findTestObject('RotaDiaria/td_'+local), 0)
	}

	@When("I click on button Remover on the RotaDiaria")
	def i_click_on_button_Remover_on_the_RotaDiaria() {

		WebUI.click(findTestObject('RotaDiaria/button_Remover'))
		'Accept alert when the alert is shown'
		WebUI.acceptAlert()
	}

	@Then("I verify the ausence of {string} on the list of RotaDiaria")
	def i_verify_the_ausence_of_on_the_list_of_RotaDiaria(String local) {
		WebUI.verifyElementNotPresent(findTestObject('RotaDiaria/td_'+local), 0)
	}

	@Then("I verify the presence of the RotaDiaria page")
	def i_verify_the_presence_of_the_RotaDiaria_page() {
		WebUI.verifyElementPresent(findTestObject('RotaDiaria/h3_Minha Rota'), 0)
		WebUI.verifyElementPresent(findTestObject('RotaDiaria/div_AVG Qualidade do Ar'), 0)
	}

	@Then("I verify the Average of the air quality")
	def i_verify_the_Average_of_the_air_quality() {
		WebUI.verifyElementPresent(findTestObject('RotaDiaria/div_AVG Qualidade do Ar'), 0)
	}
	
	@Then("I verify the presence of {string} on the rota Diaria")
	def i_verify_the_presence_of_on_the_rota_Diaria(String element) {

			WebUI.verifyElementPresent(findTestObject('RotaDiaria/p_'+element), 0)
	
	}
	@Then("I verify the text {string} of {string} on the rota Diaria")
	def i_verify_the_text_of_on_the_rota_Diaria(String text,String element) {
	
			WebUI.verifyElementText(findTestObject('RotaDiaria/p_'+element), text)
	}
}