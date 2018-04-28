/**
 *  X10 Switch Lamp Generic
 *
 *  Copyright 2017 Ronald Vargo
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
	definition (name: "X10 Switch Lamp Generic", namespace: "RonV42", author: "Ronald Vargo") {
		capability "Switch"
        capability "Switch Level"
//        capability "Actuator"
	}


	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		// TODO: define your main and details tiles here
        
        multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true){
			tileAttribute ("device.switch", key: "PRIMARY_CONTROL") {
				attributeState "on", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#00a0dc", nextState:"off"
				attributeState "off", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"on"
			}
			tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"switch level.setLevel"
			}
		}
        
/*		standardTile("switch", "device.switch", width:6, height:4, canChangeIcon:true) {
            state "off", label:'Off', icon:"st.switches.switch.off", backgroundColor:"#ffffff", action:"switch.on", nextState:"on"
            state "on", label:'On', icon:"st.switches.switch.on", backgroundColor:"#79b821", action:"switch.off", nextState:"off"
        }
*/
        
/*        standardTile("bright", "device.switch", inactiveLabel:false, decoration:"flat") {
            state "default", label:'Bright', icon:"st.custom.buttons.add-icon", action:"bright"
        }
*/        

/*		controlTile("levelSliderControl", "device.level", "slider", height: 2, width: 2, range:"(0..100)") {
    		state "level", action:"switch level.setLevel"

		}
*/
	}
    
    main(["switch"])

//	details(["switch", "bright", "dim", "networkId", "debug"])
//	details(["level"])
    
    simulator {
            // status messages
            status "Switch On": "switch:1"
            status "Switch Off": "switch:0"
        }
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'switch' attribute

}

// handle commands
def on() {
	log.debug "Executing 'on'"
	// TODO: handle 'on' command
}

def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
}

def setLevel(level) {
	log.debug "Executing 'setLevel' level:${level}"
	// TODO: handle 'setLevel' command
}
    
def setLevel(level,rate) {
	log.debug "Executing 'setLevel' level:${level} rate:${rate}"
	// TODO: handle 'setLevel' command
}


