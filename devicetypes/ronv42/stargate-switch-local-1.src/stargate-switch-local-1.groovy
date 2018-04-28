/**
 *  Stargate Switch (LOCAL)
 *
 *  Copyright 2818 ronv42@outlook.com
 *  
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "Stargate Switch (LOCAL)-1", namespace: "RonV42", author: "Ron Vargo") {
		capability "Switch"
        capability "Switch Level"
        capability "Actuator"

	}

    preferences {
    	input name: "WebxIP", type: "string", title: "Web Xpander IP Address", description: "Please enter your Web Xpander IP Address", required: true, displayDuringSetup: true
		input name: "WebxPort", type: "string", title: "Web Xpander Port", description: "Please enter your Web Xpander Port", required: true, displayDuringSetup: true
		input name: "x10ID", type: "string", title: "X10 ID", description: "Please enter the devices X10 ID", required: true, displayDuringSetup: true
    	input name: "WebxUsername", type: "string", title: "Web Xpander Username", description: "Please enter your Web Xpander Username", required: true, displayDuringSetup: true
		input name: "WebxPassword", type: "string", title: "Web Xpander Password", description: "Please enter your Web Xpander Password", required: true, displayDuringSetup: true
   }

	simulator {
		// status messages
		status "on": "on/off: 1"
		status "off": "on/off: 0"
        log.debug "WebxiP is: $WebxIP"

		// reply messages
		reply "on-off on": "on/off: 1"
		reply "on-off off": "on/off: 0"
        
	}

	// UI tile definitions
	tiles {
    
    
    multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: false) {
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.Lighting.light11", backgroundColor:"#00A0DC", nextState:"turningOff"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.Lighting.light13", backgroundColor:"#ffffff", nextState:"turningOn"
				attributeState "turningOn", label:'${name}', action:"switch.off", icon:"st.Lighting.light11", backgroundColor:"#00A0DC", nextState:"turningOff"
				attributeState "turningOff", label:'${name}', action:"switch.on", icon:"st.Lighting.light13", backgroundColor:"#ffffff", nextState:"turningOn"

			}
            
            tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"switch level.setLevel"
			}
	}
    
//    	standardTile("switch", "device.switch", width: 2, height: 2) {
//    		state "off", label: "off", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
//    		state "on", label: "on", icon: "st.switches.switch.on", backgroundColor: "#00a0dc"
//        }
    

		main ("switch")
		details ("switch", "level")
	}
}

// handle commands

def on() {
	//log.debug "Executing 'take'"
    def host = "192.168.10.160"
    log.debug "host is: $host"
    
    def port = "80"
    log.debug "port is: $port"
    
    def method = "get"
    log.debug "method is: $method"

	def path = "/sendcommand2.cgi?var1=%23%23%252b020906020000&var2=str&var3=%23%230&var4=webx&var5=webx"
    log.debug "path is: $path"
    sendEvent(name: "switch", value: "on")
    
	def x10HouseCodeMap = [
		A: "06",
		B: "07",
		C: "04",
		D: "05",
		E: "08",
		F: "09",
		G: "0a",
		H: "0b",
		I: "0e",
		J: "0f",
		K: "0c",
		L: "0d",
		M: "00",
		N: "01",
		O: "02",
        P: "03"] 	
    
    def x10HouseCode = "A"
    def value2 = x10HouseCodeMap[x10HouseCode]
    def value3 = x10HouseCodeLookup[x10HouseCode]

    log.debug "house code is: $value2"
    log.debug "house code from function is: $value3"


	
//    def headers.put("HOST", "${host}:${port}")
    

    try {
    
    	def result = new physicalgraph.device.HubAction(
    		method: "GET",
    		path: "/sendcommand2.cgi?var1=%23%23%252b020906020000&var2=str&var3=%23%230&var4=webx&var5=webx",
    		headers: [HOST: "192.168.10.160:80"]
			)
        log.debug "result is: $result"
    	return result

    }

catch (Exception e) {
    log.debug "Hit Exception on $hubAction"
    log.debug e
    }
}

def off() {
	//log.debug "Executing 'take'"
    sendEvent(name: "switch", value: "off")

    def host = "192.168.10.160"
    log.debug "host is: $host"
    
    def port = "80"
    log.debug "port is: $port"
    
    def method = "get"
    log.debug "method is: $method"

	def path = "/sendcommand2.cgi?var1=%23%23%252b020906010000&var2=str&var3=%23%230&var4=webx&var5=webx"
    log.debug "path is: $path"

	
//    def headers.put("HOST", "${host}:${port}")


    try {
    	def result = new physicalgraph.device.HubAction(
   			method: "GET",
    		path: "/sendcommand2.cgi?var1=%23%23%252b020906010000&var2=str&var3=%23%230&var4=webx&var5=webx",
    		headers: [HOST: "192.168.10.160:80"]
		)
        log.debug "result is: $result"
    	return result
    }

catch (Exception e) {
    	log.debug "Hit Exception on $hubAction"
    	log.debug e
    }
}


def level() {
	//log.debug "Executing 'take'"
    //sendEvent(name: "switch", value: "off")

    def host = "192.168.10.160"
    log.debug "host is: $host"
    
    def port = "80"
    log.debug "port is: $port"
    
    def method = "get"
    log.debug "method is: $method"

	def path = "/sendcommand2.cgi?var1=%23%23%252b020906010000&var2=str&var3=%23%230&var4=webx&var5=webx"
    log.debug "path is: $path"

	
//    def headers.put("HOST", "${host}:${port}")


    try {
    	def result = new physicalgraph.device.HubAction(
   			method: "GET",
    		path: "/sendcommand2.cgi?var1=%23%23%252b020906010000&var2=str&var3=%23%230&var4=webx&var5=webx",
    		headers: [HOST: "192.168.10.160:80"]
		)
        log.debug "result is: $result"
    	return result
    }

	catch (Exception e) {
    	log.debug "Hit Exception on $hubAction"
    	log.debug e
    }
    
}
    
private x10HouseCodeLookup() {[
		A: "06",
		B: "07",
		C: "04",
		D: "05",
		E: "08",
		F: "09",
		G: "0a",
		H: "0b",
		I: "0e",
		J: "0f",
		K: "0c",
		L: "0d",
		M: "00",
		N: "01",
		O: "02",
        P: "03"]}	



private setConfigured(configure) {
	updateDataValue("configured", configure)
}

private isConfigured() {
	getDataValue("configured") == "true"
}
