# 简版Weex项目
> `native` 表示客户端原生系统

## native提供的自定义模块
### 刷新
注册WXModule名称：common
示例：
```
var common = weex.requireModule('common');
common.reloadPage();
```

### 支付
注册WXModule名称：pay
示例：
```
var pay = weex.requireModule('pay');
pay.payByOrder(10, "", event =>{});
```
说明：
```
/**
 * 使用预支付订单信息支付
 *
 * @param type 支付类型 5:微信支付 / 10:支付宝支付
 * @param jsonData WX传过来的jsonData
 * @param callback 回调
 */
@JSMethod(uiThread = true)
public void payByOrder(int type, String jsonData, final JSCallback callback) {}
```

### 数据存储
注册WXModule名称：data
说明：
```
@JSMethod(uiThread = true)
public void setItem(String key, String value) {}

@JSMethod(uiThread = true)
public void removeItem(String key) {}

@JSMethod(uiThread = true)
public void getItem(String key) {}
```

### 页面跳转
注册WXModule名称：jump
示例：
```
var jump = weex.requireModule('jump');
jump.open("", event =>{});
```
说明：
```
/**
 * 跳转到对应界面
 *
 * @param jsonData {@link }
 * @param callback
 */
@JSMethod(uiThread = true)
public void open(String jsonData, JSCallback callback) {}

    /**
     * 名称
     */
    private String name;
    /**
     * 链接类型 {@link InsideJump.LinkTypes}
     */
    private int linkType; //1002 
    
    /**
     * native 界面Id地址<br/>
     * 1) 当前在客户端native跳转时传递native 界面Id<br/>
     */
    private int destination; 32
    
    /**
     * 是否需要带公共参数(是否需要用户信息)<br/>
     * 1) 1:需要;<br/>
     * 2) 0:不需要<br/>
     */
    private int commPara;
    
    /**
     * 附加参数<br/>
     * 1) url/weex/下载链接跳转时传递跳转的url地址<br/>
     * 2) 客户端native跳转时传递参数,参数格式为Json<br/>
     */
    private String addition;
    
{"linkType":1001,"destination":33}
{"linkType":1002,"addition":""}
```


## weex采坑

### 样式差异
- `weex`中的样式不支持简写, 所有类似`margin: 0 0 10px 10px`的都是**不支持**的
- 必须写完整，如`background:#000`需要写成`background-color:#000`
- `weex`默认使用`750px * 1334px`作为适配尺寸, 实际渲染时由于浮点数的误差可能会存在几`px`的误差, 出现细线等样式问题, 可以通过加减几个`px`来调试
- 即使使用了预处理器, css嵌套的写法也是会导致**样式失效**的
- 不支持`display: none;`
- 不支持`css`动画和`3D`样式
- `weex`中的所有`css`属性值的单位均为`px`，也可省略不写，系统会默认为`px`单位
- `weex`中只支持单个类名选择器，不支持关系选择器，也不支持属性选择器；标签中可以添加多个样式类名`<div class="one two three"><div>`
    ```
    /* 支持单个类名选择器 */
    .one-class {font-size: 36px;}
    /* 不支持关系选择器 */
    .parent > .child {padding-top: 10px;}
    /* 不支持属性选择器，不支持 v-cloak指令 */
    [v-cloak] {color: #FF6600;}
    ```

### 页面间的数据传递
- `native` -> `weex`：可以在`native`端调用打开页面时传入的`option`中定义参数，在`weex`中使用`weex.config.params`取出数据
- `weex` -> `weex`：使用 `storage` 或 `vuex`
- `weex` -> `native`：使用`native`定义的`module` 

### 其它
- `1px` = `0.013335rem`
