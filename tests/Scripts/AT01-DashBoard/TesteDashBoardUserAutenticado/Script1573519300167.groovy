import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:3000/')

WebUI.click(findTestObject('DashBoardObjects/a_Login'))

WebUI.setText(findTestObject('login Objects/input_Email'), 'example@gmail.com')

WebUI.setEncryptedText(findTestObject('login Objects/input_Password'), 'CSt51UwWECc=')

WebUI.click(findTestObject('login Objects/input_Manter a conta aberta'))

WebUI.click(findTestObject('login Objects/button_Login'))

WebUI.verifyElementPresent(findTestObject('DashBoardLoggado/div_Temperatura'), 0)

WebUI.verifyElementPresent(findTestObject('DashBoardLoggado/div_Humidade'), 0)

WebUI.verifyElementPresent(findTestObject('DashBoardObjects/div_Qualidade do Ar'), 0)

WebUI.verifyElementPresent(findTestObject('login Objects/button_Log Out'), 0)

WebUI.verifyElementNotPresent(findTestObject('DashBoardObjects/a_Login'), 0)

WebUI.verifyElementNotPresent(findTestObject('DashBoardObjects/a_Register'), 0)

