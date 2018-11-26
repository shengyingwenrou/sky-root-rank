/**
 * Created by sky.song on 2018/6/6.
 */
const storageUtils = {

  setValueByKey: function(key,value){
    localStorage.setItem(key,value);
  },

  getNumberByKey: function(key){
    let strValue =localStorage.getItem(key);
    if(strValue){
      let intValue=parseInt(strValue);
      return intValue;
    }
    return null;
  },
  getStrByKey: function(key){
    let strValue =localStorage.getItem(key);

    if(strValue){
      return strValue;
    }
    return null;
  },

  clear: function(){
    localStorage.clear();
  }
}
export default storageUtils
