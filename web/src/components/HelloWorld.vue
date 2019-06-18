<template>
  <div style="display: flex;justify-content: center;flex-direction:column;align-items:center">
    <div style="font-size:19px;margin-top: 16px;margin-bottom: 50px;">登陆系统</div>
    <Form :model="form" label-position="left" :label-width="50" style="width: 85%">
      <FormItem style="font-size:19px" label="账  号">
        <Input v-model="form.phone" placeholder="请输入手机号"></Input>
      </FormItem>
      <FormItem style="font-size:19px" label="验证码">
        <Input v-model="form.code" placeholder="请输入验证码"></Input>
      </FormItem>
    </Form>
    <Button type="success" style="width: 85%" long @click="login">确认</Button>
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
        msg: 'Welcome to Your Vue.js App',
        form: {}
      }
    },
    methods: {
      login: function () {
        return Uuid.v1();
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
