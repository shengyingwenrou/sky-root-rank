// initial state
const state = {
  tenantId: 0,
  rechargePage: {
    name: 'RechargeTimeByMoney',
    path: '/recharge/time_by_money'
  }
};

// getters
const getters = {};

// actions
const actions = {
  setTenantId({commit, state}, value) {
    commit('storeTenantId', value)
  },
  setRechargePage({commit, state}, value) {
    commit('storeRechargePage', value)
  }
};

// mutations
const mutations = {
  storeTenantId(state, value) {
    state.tenantId = value;
  },
  storeRechargePage(state, value) {
    state.rechargePage = value;
  }
};

export default {
  state,
  getters,
  actions,
  mutations
}