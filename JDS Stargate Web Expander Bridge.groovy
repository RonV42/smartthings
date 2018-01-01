/**
 *  JDS Web Expander
 *
 *  This SmartApp allows integration of X10 appliance modules, lamp modules,
 *  switches and dimmers with SmartThings with JDS Web Expander. Please visit
 *  https:.... for more
 *  information.
 *
 *  Copyright (c) 2017 RonV42 as defined on GitHub
 *
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the Free
 *  Software Foundation, either version 3 of the License, or (at your option)
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 *  for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  The latest version of this file can be found at:
 *  https://....
 *
 *  Useful links:
 *  - project page: https://github.com/statusbits/smartthings/blob/master/X10Bridge
 *  - 
 *
 *  Revision History
 *  ----------------
 *  
 */
definition(
    name: "JDS Stargate Web Exander Bridge",
    namespace: "ronv42",
    author: "ronv42",
    description: "Connect X10 switches and dimmers to SmartThings. Requires JDS Stargate Web Expander.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    //oauth: true
)

preferences {
    page name:"setupInit"
    page name:"setupMenu"
    page name:"setupMochad"
    page name:"setupAddSwitch"
    page name:"setupActionAdd"
    page name:"setupListDevices"
    page name:"setupTestConnection"
    page name:"setupActionTest"
}

private def setupInit() {
    TRACE("setupInit()")
 
    if (state.setup) {
        // already initialized, go to setup menu
        return setupMenu()
    }
 
    // initialize app state and show welcome page
    state.setup = [:]
    state.setup.installed = false
    state.devices = [:]
    return setupWelcome()
}

// Show setup welcome page
private def setupWelcome() {
    TRACE("setupWelcome()")
 
    def textPara1 =
        "X10 Bridge allows you integrate X10 switches and dimmers into " +
        "SmartThings. Please note that it requires a Linux box running " +
        "Mochad server installed on the local network and accessible from " +
        "the SmartThings hub.\n\n" +
        "Mochad is a free, open-source X10 gateway software for Linux. " +
        "Please visit [insert link] for X10 Bridge setup instructions."
 
    def textPara2 = "${app.name}. ${textVersion()}\n${textCopyright()}"
 
    def textPara3 =
        "Please read the License Agreement below. By tapping the 'Next' " +
        "button at the top of the screen, you agree and accept the terms " +
        "and conditions of the License Agreement."
 
    def textLicense =
        "This program is free software: you can redistribute it and/or " +
        "modify it under the terms of the GNU General Public License as " +
        "published by the Free Software Foundation, either version 3 of " +
        "the License, or (at your option) any later version.\n\n" +
        "This program is distributed in the hope that it will be useful, " +
        "but WITHOUT ANY WARRANTY; without even the implied warranty of " +
        "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU " +
        "General Public License for more details.\n\n" +
        "You should have received a copy of the GNU General Public License " +
        "along with this program. If not, see <http://www.gnu.org/licenses/>."
 
    def pageProperties = [
        name        : "setupInit",
        title       : "Welcome!",
        nextPage    : "setupMenu",
        install     : false,
        uninstall   : state.setup.installed
    ]
 
    return dynamicPage(pageProperties) {
        section {
            paragraph textPara1
            paragraph textPara2
            paragraph textPara3
        }
        section("License") {
            paragraph textLicense
        }
    }
}
