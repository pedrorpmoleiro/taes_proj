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

WebUI.click(findTestObject('Page_Sentinel_Proyect/a_Login'))

WebUI.setText(findTestObject('Page_Sentinel_Proyect/input_Email_emailLogin'), 'example@gmail.com')

WebUI.setEncryptedText(findTestObject('Page_Sentinel_Proyect/input_Password_passwordLogin'), 'CSt51UwWECc=')

WebUI.click(findTestObject('Page_Sentinel_Proyect/button_Login'))

WebUI.click(findTestObject('Object Repository/test/button_Locais'))

WebUI.click(findTestObject('RotaDiaria/td_AS01'))

WebUI.verifyOptionSelectedByValue(findTestObject('DashBoardLoggado/select_locais'), '3372783', false, 0)

WebUI.click(findTestObject('Object Repository/test/img_Log Out_location_img'))

WebUI.closeBrowser()

