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

WebUI.click(findTestObject('Object Repository/AT5Adicionar/Page_Sentinel_Proyect/a_Login'))

WebUI.setText(findTestObject('AT5Adicionar/Page_Sentinel_Proyect/input_Email___BVID__16'), 'andrestoledo@outlook.pt')

WebUI.setEncryptedText(findTestObject('Object Repository/AT5Adicionar/Page_Sentinel_Proyect/input_Password'), 'CSt51UwWECc=')

WebUI.click(findTestObject('Object Repository/AT5Adicionar/Page_Sentinel_Proyect/button_Login'))

WebUI.click(findTestObject('Object Repository/AT5Adicionar/Page_Sentinel_Proyect/button_Locais'))

WebUI.selectOptionByValue(findTestObject('AT5Adicionar/Page_Sentinel_Proyect/select_'), '2267095', true)

WebUI.click(findTestObject('Object Repository/AT5Adicionar/Page_Sentinel_Proyect/button_Adicionar'))

WebUI.click(findTestObject('DashBoardLoggado/td_DAF-12'))

WebUI.click(findTestObject('Object Repository/AT5Adicionar/Page_Sentinel_Proyect/h4_Locais_Favoritos'))

WebUI.closeBrowser()

