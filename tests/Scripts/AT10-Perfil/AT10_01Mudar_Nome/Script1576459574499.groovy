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

WebUI.click(findTestObject('Object Repository/AT10_Perfil/a_Login'))

WebUI.setText(findTestObject('Object Repository/AT10_Perfil/input_Email_emailLogin'), 'example@gmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/AT10_Perfil/input_Password_passwordLogin'), 'CSt51UwWECc=')

WebUI.click(findTestObject('Object Repository/AT10_Perfil/button_Login'))

WebUI.click(findTestObject('Object Repository/AT10_Perfil/button_Perfil'))

WebUI.click(findTestObject('Perfil/button_Alterar Nome'))

WebUI.setText(findTestObject('Perfil/input_Name_namePerfil'), '')

WebUI.click(findTestObject('Perfil/button_Alterar'))

WebUI.verifyElementPresent(findTestObject('Perfil/strong_Required'), 0)

WebUI.setText(findTestObject('Perfil/input_Name_namePerfil'), 'lo')

WebUI.click(findTestObject('Perfil/button_Alterar'))

WebUI.verifyElementPresent(findTestObject('Perfil/strong_Too short'), 0)

WebUI.setText(findTestObject('Perfil/input_Name_namePerfil'), 'exemplo Ex')

WebUI.click(findTestObject('Perfil/button_Alterar'))

WebUI.verifyElementPresent(findTestObject('Perfil/strong_Name Update successfully'), 0)

WebUI.click(findTestObject('Perfil/a_Voltar'))

WebUI.verifyElementText(findTestObject('DashBoardLoggado/h1_Bem-vindoNome'), 'Bem-vindo exemplo Ex')

WebUI.closeBrowser()

