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

WebUI.click(findTestObject('Object Repository/AT10_Pass/a_Login'))

WebUI.setText(findTestObject('Object Repository/AT10_Pass/input_Email_emailLogin'), 'example@gmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/AT10_Pass/input_Password_passwordLogin'), 'CSt51UwWECc=')

WebUI.click(findTestObject('Object Repository/AT10_Pass/button_Login'))

WebUI.click(findTestObject('Object Repository/AT10_Pass/button_Perfil'))

WebUI.click(findTestObject('Perfil/button_Alterar Password'))

WebUI.setEncryptedText(findTestObject('Perfil/input_New Password_passwordPerfil'), 'N2yfgUfNEt0=')

WebUI.setEncryptedText(findTestObject('Perfil/input_Password                Confirmation__4e007c'), 
    'N2yfgUfNEt0=')

WebUI.click(findTestObject('Perfil/button_Alterar_Pass'))

WebUI.click(findTestObject('Object Repository/AT10_Pass/a_Voltar'))

WebUI.click(findTestObject('Object Repository/AT10_Pass/button_Log Out'))

WebUI.closeBrowser()

