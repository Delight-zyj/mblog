module.exports = {
  root: true,
  env: {
    node: true,
  },
  extends: [
    'plugin:vue/vue3-essential',
    '@vue/airbnb',
  ],
  parserOptions: {
    parser: '@babel/eslint-parser',
  },
  rules: {
    'indent': 'off',
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'linebreak-style': ['error', 'windows'],
    'vue/multi-word-component-names': 'off',
    'comma-dangle': 'off',
    'semi': 'off',
    'space-before-blocks': 'off',
    'no-unused-vars': 'off',
    'arrow-body-style': ["error", "as-needed"],
    'no-unused-vars': 'off'
  },
};
