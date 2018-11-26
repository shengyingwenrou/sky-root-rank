// https://eslint.org/docs/user-guide/configuring

module.exports = {
  root: true,
  parserOptions: {
    parser: 'babel-eslint'
  },
  env: {
    browser: true,
  },
  extends: [
    // https://github.com/vuejs/eslint-plugin-vue#priority-a-essential-error-prevention
    // consider switching to `plugin:vue/strongly-recommended` or `plugin:vue/recommended` for stricter rules.
    'plugin:vue/essential',
    // https://github.com/standard/standard/blob/master/docs/RULES-en.md
    'standard'
  ],
  // required to lint *.vue files
  plugins: [
    'vue'
  ],
  // add your custom rules here
  rules: {
    // allow async-await
    'generator-star-spacing': 'off',
    // allow debugger during development
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    // custom
    'indent': 'off', // 缩进风格
    'quotes': [1, "single"], // 除需要转义的情况外，字符串统一使用单引号
    'keyword-spacing': 'off', // 关键字后面加空格
    'space-before-function-paren': 'off', // 函数声明时括号与函数名间加空格
    'space-infix-ops': 'off', // 字符串拼接操作符 (Infix operators) 之间要留空格
    'comma-spacing': 'off', // 逗号后面加空格
    'brace-style': 'off', // else 关键字要与花括号保持在同一行
    'no-multiple-empty-lines': 'off', // 不允许有连续多行空行
    'one-var': 'off', // 每个 var 关键字单独声明一个变量
    'block-spacing': 'off', // 单行代码块两边加空格
    'no-cond-assign': 'off', // 条件语句中赋值语句使用括号包起来
    'eol-last': 'off', // 文件末尾留一空行
    'key-spacing': 'off', // 键值对当中冒号与值之间要留空白
    'no-multi-spaces': 'off', // 除了缩进，不要使用多个空格
    'no-multi-str': 'off', // 不要使用多行字符串
    'no-return-assign': 'off', // return 语句中的赋值必需有括号包裹
    'no-trailing-spaces': 'off', // 行末不留空格
    'padded-blocks': 'off', // 代码块中避免多余留白
    'semi-spacing': 'off', // 遇到分号时空格要后留前不留
    'space-before-blocks': 'off', // 代码块首尾留空格
    'space-in-parens': 'off', // 圆括号间不留空格
    'spaced-comment': 'off', // 注释首尾留空格
    'template-curly-spacing': 'off', // 模板字符串中变量前后不加空格
    "semi": 'off', // 语句强制分号结尾
    "no-undef": 'off'
  }
}
