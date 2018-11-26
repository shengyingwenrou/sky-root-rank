import *  as types from './mutation-types'
import *  as keys from './local-storage-cache-keys'
import storageUtils from '@/utils/storageUtils'

const mutations = {



  /** 基础参数初始化以及本地缓存 **/
    [types.SET_PORTAL_CONFIG](state, payload) {
    // 只要deviceMac不为空都重新更新本地缓存
    state.portal.base_portal_id = payload.portal_id
    state.portal.base_portal_title = payload.portal_title
    state.portal.base_portal_type = payload.portal_type
    state.portal.base_portal_iconUrl = payload.portal_iconUrl
    state.portal.base_portal_backgroundUrl = payload.portal_backgroundUrl
    if (state.portal.base_portal_id) {
      storageUtils.setValueByKey(keys.KEY_PORTAL_BASE_PORTAL_ID, state.portal.base_portal_id)
    } else {
      state.portal.base_portal_id = storageUtils.getStrByKey(keys.KEY_PORTAL_BASE_PORTAL_ID)
    }
    if (state.portal.base_portal_title) {
      storageUtils.setValueByKey(keys.KEY_PORTAL_BASE_PORTAL_TITLE, state.portal.base_portal_title)
    } else {
      state.portal.base_portal_title = storageUtils.getStrByKey(keys.KEY_PORTAL_BASE_PORTAL_TITLE)
    }
    if (state.portal.base_portal_type) {
      storageUtils.setValueByKey(keys.KEY_PORTAL_BASE_PORTAL_TYPE, state.portal.base_portal_type)
    } else {
      state.portal.base_portal_type = storageUtils.getStrByKey(keys.KEY_PORTAL_BASE_PORTAL_TYPE)
    }
    if (state.portal.base_portal_iconUrl) {
      storageUtils.setValueByKey(keys.KEY_PORTAL_BASE_PORTAL_ICON_URL, state.portal.base_portal_iconUrl)
    } else {
      state.portal.base_portal_iconUrl = storageUtils.getStrByKey(keys.KEY_PORTAL_BASE_PORTAL_ICON_URL)
    }
    if (state.portal.base_portal_backgroundUrl) {
      storageUtils.setValueByKey(keys.KEY_PORTAL_BASE_PORTAL_BACKGROUND_URL, state.portal.base_portal_backgroundUrl)
    } else {
      state.portal.base_portal_backgroundUrl = storageUtils.getStrByKey(keys.KEY_PORTAL_BASE_PORTAL_BACKGROUND_URL)
    }
  },

  /** 项目配置信息初始化以及本地缓存 **/
    [types.SET_DEVICE_CONFIG](state, payload) {
    if (payload.deviceMac) {
      //storageUtils.clear()
      state.portal.base_deviceMac = payload.deviceMac
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_DEVICE_MAC, state.portal.base_deviceMac)
    } else {
      state.portal.base_deviceMac = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_DEVICE_MAC)
    }

    if (payload.terminalMac) {
      state.portal.base_terminalMac = payload.terminalMac
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_TERMINAL_MAC, state.portal.base_terminalMac)
    } else {
      state.portal.base_terminalMac = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_TERMINAL_MAC)
    }

    if (payload.terminalId) {
      state.portal.base_terminalId = payload.terminalId
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_TERMINAL_ID, state.portal.base_terminalId)
    } else {
      state.portal.base_terminalId = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_TERMINAL_ID)
    }

    if (payload.deviceId) {
      state.portal.base_deviceId = payload.deviceId
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_DEVICE_ID, state.portal.base_deviceId)
    } else {
      state.portal.base_deviceId = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_DEVICE_ID)
    }

    if (payload.projectId) {
      state.portal.base_projectId = payload.projectId
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_PROJECT_ID, state.portal.base_projectId)
    } else {
      state.portal.base_projectId = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_PROJECT_ID)
    }

    if (payload.projectName) {
      state.portal.base_projectName = payload.projectName
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_PROJECT_NAME, state.portal.base_projectName)
    } else {
      state.portal.base_projectName = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_PROJECT_NAME)
    }

    if (payload.gwAddress) {
      state.portal.base_gwAddress = payload.gwAddress
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_GW_ADDRESS, state.portal.base_gwAddress)
    } else {
      state.portal.base_gwAddress = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_GW_ADDRESS)
    }

    if (payload.gwPort) {
      state.portal.base_gwPort = payload.gwPort
      storageUtils.setValueByKey(keys.KEY_DEVICEBASE_GW_PORT, state.portal.base_gwPort)
    } else {
      state.portal.base_gwPort = storageUtils.getStrByKey(keys.KEY_DEVICEBASE_GW_PORT)
    }

    if (payload.gwtype) {
      state.portal.base_gwtype = payload.gwtype
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_GW_TYPE, state.portal.base_gwtype)
    } else {
      state.portal.base_gwtype = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_GW_TYPE)
    }

    if (payload.partnerDevice) {
      state.portal.base_partnerDevice = payload.partnerDevice
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_PARTNER_DEVICE, state.portal.base_partnerDevice)
    } else {
      state.portal.base_partnerDevice = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_PARTNER_DEVICE)
    }

    if (payload.speciallyTypePath) {
      state.portal.base_speciallyTypePath = payload.speciallyTypePath
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_SPECIALLY_TYPE_PATH, state.portal.base_portalSpeciallyTypePath)
    } else {
      state.portal.base_portalSpeciallyTypePath = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_SPECIALLY_TYPE_PATH)
    }

    if (payload.clientType) {
      state.portal.base_clientType = payload.clientType
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_CLIENT_TYPE, state.portal.base_clientType)
    } else {
      state.portal.base_clientType = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_CLIENT_TYPE)
    }

    if (payload.connectType) {
      state.portal.base_connectType = payload.connectType
      storageUtils.setValueByKey(keys.KEY_DEVICE_BASE_CONNECT_TYPE, state.portal.base_connectType)
    } else {
      state.portal.base_connectType = storageUtils.getStrByKey(keys.KEY_DEVICE_BASE_CONNECT_TYPE)
    }
  },

  /** 项目配置信息初始化以及本地缓存 **/
    [types.SET_SYS_CONFIG](state, payload) {
    if (payload.platform) {
      state.sys.platform = payload.platform
      storageUtils.setValueByKey(keys.KEY_SYS_BASE_PLATFORM, state.sys.platform)
    } else {
      state.sys.platform = storageUtils.getStrByKey(keys.KEY_SYS_BASE_PLATFORM)
    }
  },
  /** 设置登录账户信息以及本地缓存 **/
    [types.SET_USER_LOGIN_INFO](state, payload) {
    if (payload.token) {
      state.user.base_token = payload.token
      storageUtils.setValueByKey(keys.KEY_USER_BASE_TOKEN, state.user.base_token)
    } else {
      state.user.base_token = storageUtils.getStrByKey(keys.KEY_USER_BASE_TOKEN)
    }

    if (payload.accountId) {
      state.user.base_accountId = payload.accountId
      storageUtils.setValueByKey(keys.KEY_USER_BASE_ACCOUNT_ID, state.user.base_accountId)
    } else {
      state.user.base_accountId = storageUtils.getStrByKey(keys.KEY_USER_BASE_ACCOUNT_ID)
    }
    if (payload.username) {
      state.user.base_username = payload.username
      storageUtils.setValueByKey(keys.KEY_USER_BASE_UESR_NAME, state.user.base_username)
    } else {
      state.user.base_username = storageUtils.getStrByKey(keys.KEY_USER_BASE_UESR_NAME)
    }
    if (payload.isRegister) {
      state.user.base_isRegister = payload.isRegister
      storageUtils.setValueByKey(keys.KEY_USER_BASE_IS_REGISTER, state.user.base_isRegister)
    } else {
      state.user.base_isRegister = storageUtils.getStrByKey(keys.KEY_USER_BASE_IS_REGISTER)
    }
    if (payload.authCode) {
      state.user.base_authCode = payload.authCode
      storageUtils.setValueByKey(keys.KEY_USER_BASE_AUTH_CODE, state.user.base_authCode)
    } else {
      state.user.base_authCode = storageUtils.getStrByKey(keys.KEY_USER_BASE_AUTH_CODE)
    }
  },

  /** 本地缓存测试 **/
  [types.SET_TEST_COUNT](state) {
    let count=storageUtils.getNumberByKey(keys.KEY_TEST_COUNT);
    if (count) {
      state.count = count + 2
    } else {
      state.count = 2
    }
    storageUtils.setValueByKey(keys.KEY_TEST_COUNT, state.count);
  },

  /** 联网页_设置联网结果信息 **/
    [types.SET_CONNECT_PAGE_CONNECT_PROCESS](state, payload) {
    if (payload) {
      state.CONNECT_PAGE_CONNECT_PROCESS = payload
    }
  },
  [types.SET_CONNECT_PAGE_CONNECT_MESSAGE](state, payload) {
    if (payload) {
      state.CONNECT_PAGE_CONNECT_MESSAGE = payload
    }
  }

}
export default mutations
