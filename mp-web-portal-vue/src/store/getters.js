



export const baseDeviceMac = state => state.portal.base_deviceMac
export const baseTerminalMac = state => state.portal.base_terminalMac

export const configBaseInfo = state => state.portal.base_deviceMac
export const configSettingInfo = state => state.portal.base_deviceMac
export const configPortalInfo = state => state.portal.base_deviceMac

export const countAnother = state => state.numb
export const counttodos = state => state.todos
export const fruit = state => state.todos.filter(todo => todo.done)
export const fruitTodos = state => state.todos


export const connectPageConnectProcess = state => state.CONNECT_PAGE_CONNECT_PROCESS+'%'
export const connectpageConnectMessage = state => state.CONNECT_PAGE_CONNECT_MESSAGE
