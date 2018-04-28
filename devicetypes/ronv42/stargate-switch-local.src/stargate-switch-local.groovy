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
	definition (name: "Stargate Switch (LOCAL)", namespace: "RonV42", author: "Ron Vargo") {
		capability "Switch"
		capability "Sensor"
		capability "Actuator"

	}

    preferences {
    input("WebxIP", "string", title:"Web Xpander IP Address", description: "Please enter your Web Xpander IP Address", defaultValue: "192.168.10.160", required: true, displayDuringSetup: true)
    input("WebxPort", "string", title:"Web Xpander Port", description: "Please enter your Web Xpander Port", defaultValue: 80, required: true, displayDuringSetup: true)
    input("x10ID", "string", title:"X10 ID", description: "Please enter the devices Insteon ID - numbers only", defaultValue: "A1", required: true, displayDuringSetup: true)
    input("WebxUsername", "string", title:"Web Xpander Username", description: "Please enter your Web Xpander Username", defaultValue: "webx" , required: true, displayDuringSetup: true)
    input("WebxPassword", "string", title:"Web Xpander Password", description: "Please enter your Web Xpander Password", defaultValue: "webx" , required: true, displayDuringSetup: true)
   }

	simulator {
		// status messages
		status "on": "on/off: 1"
		status "off": "on/off: 0"

		// reply messages
		reply "on-off on": "on/off: 1"
		reply "on-off off": "on/off: 0"
	}

	// UI tile definitions
	tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
			state "off", label: '${name}', action: "switch.on", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
			state "on", label: '${name}', action: "switch.off", icon: "st.switches.switch.on", backgroundColor: "#79b821"
		}

		main "switch"
		details "switch"
	}
}

// handle commands
def on() {
	//log.debug "Executing 'take'"
    sendEvent(name: "switch", value: "on")
    def host = "192.168.10.160"
    log.debug "host is: $host"
    
    def port = "80"
    log.debug "port is: $port"
    
    def method = "get"
    log.debug "method is: $method"

	def path = "/sendcommand2.cgi?var1=%23%23%252b020906020000&var2=str&var3=%23%230&var4=webx&var5=webx"
    log.debug "path is: $path"

	
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