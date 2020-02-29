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

WebUI.navigateToUrl('http://localhost:3000/auth/login')

WebUI.verifyElementText(findTestObject('login Objects/Page_Sentinel_Proyect/div_Login'), 'Login')

WebUI.setText(findTestObject('login Objects/input_Email'), '')

WebUI.setText(findTestObject('login Objects/input_Password'), '')

WebUI.click(findTestObject('login Objects/button_Login'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.setText(findTestObject('login Objects/input_Email'), '123kenalx@gmail.com')

WebUI.setText(findTestObject('login Objects/input_Password'), '')

WebUI.click(findTestObject('login Objects/button_Login'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.setText(findTestObject('login Objects/input_Email'), '')

WebUI.setText(findTestObject('login Objects/input_Password'), '123123')

WebUI.click(findTestObject('login Objects/button_Login'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.navigateToUrl('http://localhost:3000/auth/register')

WebUI.verifyElementText(findTestObject('Register_Objects/div_Register'), 'Register')

WebUI.setText(findTestObject('Register_Objects/input_Register_Name'), '')

WebUI.setText(findTestObject('Register_Objects/input_Register_Email'), '')

WebUI.setText(findTestObject('Register_Objects/input_Register_Password'), '')

WebUI.setText(findTestObject('Register_Objects/input_Register_PasswordConfirmation'), '')

WebUI.click(findTestObject('Register_Objects/button_Register'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.setText(findTestObject('Register_Objects/input_Register_Name'), 'kenalx')

WebUI.setText(findTestObject('Register_Objects/input_Register_Email'), 'kenalx@gmail.com')

WebUI.setText(findTestObject('Register_Objects/input_Register_Password'), '123123')

WebUI.setText(findTestObject('Register_Objects/input_Register_PasswordConfirmation'), '')

WebUI.click(findTestObject('Register_Objects/button_Register'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.setText(findTestObject('Register_Objects/input_Register_Name'), '')

WebUI.setText(findTestObject('Register_Objects/input_Register_Email'), 'kenalx@gmail.com')

WebUI.setText(findTestObject('Register_Objects/input_Register_Password'), '123123')

WebUI.setText(findTestObject('Register_Objects/input_Register_PasswordConfirmation'), '123123')

WebUI.click(findTestObject('Register_Objects/button_Register'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.setText(findTestObject('Register_Objects/input_Register_Name'), 'kenalx')

WebUI.setText(findTestObject('Register_Objects/input_Register_Email'), '')

WebUI.setText(findTestObject('Register_Objects/input_Register_Password'), '123123')

WebUI.setText(findTestObject('Register_Objects/input_Register_PasswordConfirmation'), '123123')

WebUI.click(findTestObject('Register_Objects/button_Register'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.setText(findTestObject('Register_Objects/input_Register_Name'), 'kenalx')

WebUI.setText(findTestObject('Register_Objects/input_Register_Email'), 'kenalx@gmail.com')

WebUI.setText(findTestObject('Register_Objects/input_Register_Password'), '')

WebUI.setText(findTestObject('Register_Objects/input_Register_PasswordConfirmation'), '123123')

WebUI.click(findTestObject('Register_Objects/button_Register'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

WebUI.setText(findTestObject('Register_Objects/input_Register_Name'), 'kenalx')

WebUI.setText(findTestObject('Register_Objects/input_Register_Email'), 'kenalx@gmail.com')

WebUI.setText(findTestObject('Register_Objects/input_Register_Password'), '123123')

WebUI.setText(findTestObject('Register_Objects/input_Register_PasswordConfirmation'), '')

WebUI.click(findTestObject('Register_Objects/button_Register'))

WebUI.verifyElementPresent(findTestObject('login Objects/strong_Required'), 0)

