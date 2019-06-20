<template>
  <div style="display: flex;justify-content: center;flex-direction:column;align-items:center">
    <div style="font-size:19px;margin-top: 16px;margin-bottom: 50px;">绑定手机</div>
    <div style="display: flex;justify-content: space-between;flex-direction:row;align-items:center;width: 85%;height: 63px">
      <div style="width: 20%;font-size: 16px;margin-right: 5%">账&nbsp&nbsp&nbsp号</div>
      <input type="text" placeholder="请输入手机号" style= "background-color:transparent;border:none;outline:none;width: 65%;font-size: 14px" name="t2" v-model=form.phone />
      <div @click="empty" style="display: flex;justify-content: center;flex-direction:row;align-items:center;width: 10%;height: 100%;" >
        <img src="../assets/empty.png" style="width: 16px">
      </div>
    </div>
    <div style="width: 85%;height: 1px;background: #EEEEEE"></div>
    <div style="display: flex;justify-content: space-between;flex-direction:row;align-items:center;width: 85%;height: 63px">
      <div style="width: 20%;font-size: 16px;margin-right: 5%;">验证码</div>
      <input type="text"  placeholder="请输入验证码" style= "background-color:transparent;border:none;outline:none;width: 50%;font-size: 14px" name="t2" v-model=form.code />
      <div style="color:#30C17C;font-size:14px;width: 25%;" @click="sendMessage">获取验证码</div>
    </div>
    <div style="width: 85%;height: 1px;background: #EEEEEE"></div>
    <Button type="success" style="width: 85%;height: 40px;font-size: 16px;margin-top: 30px" long @click="login">确认</Button>
    <div style="display: flex;justify-content: space-around;flex-direction:column;align-items:start;width: 85%;text-align:left;padding: 25px;margin-top: 20px;height:108px;background:rgba(248,248,248,1);font-size:14px;line-height: 22px;" v-if="showTip">
      <div>您的手机号未录入至差旅系统中，请使用差旅系统关联的手机号进行绑定。</div>
      <div>
        <span>如有疑问请拨打</span>
        <span style="color: #30C17C">400096301</span>
        <span>进行咨询。</span>
      </div>
    </div>
    <div style="display: flex;justify-content: space-around;flex-direction:column;align-items:start;text-align:left;padding: 20px;margin-top: 20px;opacity:0.6;border-radius:5px;color:rgba(255,255,255,1);background:rgba(0,0,0,1);font-size:15px;line-height: 22px;" v-if="showSend">
      {{sendTip}}
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import {Button, Form, FormItem} from 'iview';

  function checkPhone(phone,that) {
    if (!phone) {
      that.showTip = false;
      that.showSend = true;
      that.sendTip = "手机号不能为空";
      setTimeout(() => {
        that.showSend = false;
      }, 1000);
      return false;
    } else if (!/^1[34578]\d{9}$/.test(phone)) {
      that.showTip = false;
      that.showSend = true;
      that.sendTip = "手机号格式不正确";
      setTimeout(() => {
        that.showSend = false;
      }, 1000);
      return false;
    }
    return true;
  }

  function checkCode(code,that) {
    if (!code) {
      that.showTip = false;
      that.showSend = true;
      that.sendTip = "验证码不能为空";
      setTimeout(() => {
        that.showSend = false;
      }, 1000);
      return false;
    } else if (!/^\d{4}$/.test(code)) {
      that.showTip = false;
      that.showSend = true;
      that.sendTip = "验证码格式不正确";
      setTimeout(() => {
        that.showSend = false;
      }, 1000);
      return false;
    }
    return true;
  }
  export default {
    components: {
      axios,
      Button,
      Form,
      FormItem
    },
    data() {
      return {
        sendTip: '验证码已发送至你的手机请注意查收',
        showSend: false,
        showTip: false,
        form: {}
      }
    },
    beforeMount: function () {
      this.uid = this.$route.query.uid;
    },
    methods: {
      empty: function () {
        this.form.phone = null;
      },
      sendMessage: function () {
        let that = this;
        let phone = that.form.phone;
        if (!checkPhone(phone, that)) {
          return;
        }
        axios.get('/api/user/send', {
          params: {
            phone: phone
          }
        })
          .then(function (response) {
            if (response.data.errorcode === 0) {
              that.showTip = false;
              that.showSend = true;
              that.sendTip = "验证码已发送至你的手机请注意查收";
              setTimeout(() => {
                that.showSend = false;
              }, 1000);
            } else {
              that.showTip = false;
            }
          });
      },
      login: function () {
        let that = this;
        let phone = that.form.phone;
        let code = that.form.code;
        checkPhone(phone,that)
        checkCode(code,that)
        if (!checkPhone(phone, that)) {
          return;
        }
        if (!checkCode(code, that)) {
          return;
        }
        axios.get('/api/user/bind', {
          params: {
            phone: this.form.phone,
            uid: this.uid,
            code: this.form.code
          }
        })
          .then(function (response) {
            if (response.data.errorcode === 0) {
              window.location.href = 'http://localhost:9999/user/autoLogin?uid=' + that.uid;
            } else {
              that.showTip = false;
              that.showSend = true;
              that.sendTip = "验证码输入错误";
              setTimeout(() => {
                that.showSend = false;
              }, 1000);
            }
          })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
