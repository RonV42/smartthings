/**
 *  Copyright 2018 Ron Vargo
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
	definition (name: "Family Room-6 Button Remote", namespace: "RonV42", author: "RonV42") {
		capability "Actuator"
		capability "Button"
		capability "Holdable Button"
		capability "Configuration"
		capability "Sensor"

        command "push1"
        command "push2"
        command "push3"
        command "push4"
        command "push5"
        command "push6"
	}

	simulator {
		status "button 1 pushed":  "command: 2001, payload: 01"
		status "button 2 pushed":  "command: 2001, payload: 29"
		status "button 3 pushed":  "command: 2001, payload: 51"
		status "button 4 pushed":  "command: 2001, payload: 79"
		status "button 5 pushed":  "command: 2001, payload: 15"
		status "button 6 pushed":  "command: 2001, payload: 3D"
		status "wakeup":  "command: 8407, payload: "
	}
	tiles {
		standardTile("button", "device.button") {
			state "default", label: "", icon: "st.unknown.zwave.remote-controller", backgroundColor: "#ffffff"
		}
 		standardTile("push1", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: "Normal", backgroundColor: "#ffffff", action: "push1"
		}
 		standardTile("push2", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: "Bright", backgroundColor: "#ffffff", action: "push2"
		}
 		standardTile("push3", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: "Dim", backgroundColor: "#ffffff", action: "push3"
		}
 		standardTile("push4", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: "Idle", backgroundColor: "#ffffff", action: "push4"
		}
 		standardTile("dummy1", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: " ", backgroundColor: "#ffffff", action: "hold1"
		}
 		standardTile("push5", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: "Reset", backgroundColor: "#ffffff", action: "push5"
		}
 		standardTile("push6", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: "Off", backgroundColor: "#ffffff", action: "push6"
		}
 		standardTile("dummy2", "device.button", width: 1, height: 1, decoration: "flat") {
			state "default", label: " ", backgroundColor: "#ffffff", action: "hold1"
		}

		main "button"
		details(["push1","push2","button","push3","push4","dummy1","push5","push6","dummy2"])
	}
}

def parse(String description) {

}

def push1() {
	push(1)
}

def push2() {
	push(2)
}

def push3() {
	push(3)
}

def push4() {
	push(4)
}

def push5() {
	push(5)
}

def push6() {
	push(6)
}

def hold1() {
	hold(1)
}

private push(button) {
	log.debug "$device.displayName button $button was pushed"
	sendEvent(name: "button", value: "pushed", data: [buttonNumber: button], descriptionText: "$device.displayName button $button was pushed", isStateChange: true)
}

private hold(button) {
	log.debug "$device.displayName button $button was held"
	sendEvent(name: "button", value: "held", data: [buttonNumber: button], descriptionText: "$device.displayName button $button was held", isStateChange: true)
}


def installed() {
	initialize()
}

def updated() {
	initialize()
}

def initialize() {
	sendEvent(name: "numberOfButtons", value: 6)
}