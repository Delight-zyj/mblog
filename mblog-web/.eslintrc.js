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
    indent: 'off',
    semi: 'off',
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'linebreak-style': ['off'],
    'vue/multi-word-component-names': 'off',
    'comma-dangle': 'off',
    'space-before-blocks': 'off',
    'no-unused-vars': 'off',
    'arrow-body-style': 'off',
    'max-len': ['error', { code: 300 }],
    'import/no-extraneous-dependencies': ['error', { devDependencies: true }]
  },
};
