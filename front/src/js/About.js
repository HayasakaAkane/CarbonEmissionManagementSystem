
//1、引入 axios 模块
import axios from 'axios'

export default {
  data() {
    return {
      msg: "主页",
      tableData:[],
    }
  },
  
  methods:{
   getData(){
    let that = this
      var api="http://www.phonegap100.com/appapi.php?a=getPortalList&catid=20&page=1";
      //2.使用axios 进行get请求
      axios.get(api).then((res)=>{
        //请求成功的回调函数
        that.tableData = res
        console.log(res)
      }).catch((err)=>{
        //请求失败的回调函数
        console.log(err)
      })
   }

  }
};
