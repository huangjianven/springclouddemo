<template>
  <div style="display: flex;justify-content: center;flex-direction:column;align-items:center">
    <img src="../assets/bg.png" style="margin-top: 33px;width: 100%">
    <div
      style="display: flex;justify-content: center;flex-direction:row;align-items:center;margin-top: 45px;width: 85%;background:rgba(28,172,27,1);border-radius:3px;height:40px;">
      <img src="../assets/weixin.png" style="height: 17px;margin-right: 1%;">
      <div style="font-size: 17px;color:rgba(255,255,255,1)" @click="login">微信用户一键登录</div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import {Button, Form, FormItem} from 'iview';

  export default {
    components: {
      axios,
      Button,
      Form,
      FormItem
    },
    data() {
      return {
        uid: '',
      }
    },
    beforeMount: function () {
      this.uid = this.$route.query.uid;
    },
    methods: {
      login: function () {
        this.$router.push({
          path: '/bind',
          query: {
            uid: this.uid
          }
        })
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
