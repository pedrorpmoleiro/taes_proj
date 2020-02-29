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


class AT5ValidarRemover {

	@When("I verify the presence of {string} on the list of locals")
	def i_verify_the_presence_of_on_the_list_of_locals(String local) {
		WebUI.verifyElementPresent(findTestObject('Object Repository/DashBoardLoggado/td_'+local), 0)
	}

	@When("I click on the local {string} in the list of favorites")
	def i_click_on_the_local_in_the_list_of_favorites(String local) {
		'Wait for failed message to be present'
		WebUI.waitForElementPresent(findTestObject('DashBoardLoggado/td_'+local),10)
		WebUI.click(findTestObject('DashBoardLoggado/td_'+local))
	}

	@Then("I click on Remover")
	def i_click_on_Remover() {

		WebUI.click(findTestObject('Object Repository/DashBoardLoggado/button_Remover'))
		'Accept alert when the alert is shown'
		WebUI.acceptAlert()
	}

	@Then("I verify the ausence of {string} on the list of locals")
	def i_verify_the_ausence_of_on_the_list_of_locals(String local) {
		WebUI.verifyElementNotPresent(findTestObject('Object Repository/DashBoardLoggado/td_'+local), 0)
	}
}
