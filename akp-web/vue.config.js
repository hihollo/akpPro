const webpack = require('webpack')
module.exports = {
  publicPath: './',
  devServer: {
    host: 'localhost',
    port: '8181',
    proxy: {
      '/staticphoto': {
        target: 'http://www.jkdev.store', // 要跨域的域名  (部署时)
        // target: 'http://local:8080', // （开发时）
        changeOrigin: true // 是否开启跨域
      }
    }
  },
  chainWebpack: config => {
    config.plugin('provide').use(webpack.ProvidePlugin, [{
      $: 'jquery',
      jquery: 'jquery',
      jQuery: 'jquery',
      'window.jQuery': 'jquery'
    }])
  }
}
