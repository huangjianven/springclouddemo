//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    url: 'http://localhost:9999/'
  },
  onLoad: function () {
    var that=this;
    // 登录
    wx.login({
      success: res => {
        var code = res.code;
        if (code) {
          that.setData({
            url: 'http://localhost:9999/user/login?code='+code
          })
        } else {
          console.log('获取用户登录失败：' + res.errMsg);
        }
      }
    })
  }
})
