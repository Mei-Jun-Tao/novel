$(function(){
    //存储数据
    var data4Vue = {
        uri:'kinds',
        beans: [],
        user:{id:0,name:'',fileImage:{id:0}}
    };

    //创建vue
    var vue = new Vue({
        el:'#kind',
        data:data4Vue,
        mounted:function(){
            //表示vue对象加载成功后
            this.list();
            this.getUser();
        },
        methods:{
            list:function(){
                //获取访问路径l
                var url = this.uri;
                //通过异步处理访问路径
                axios.get(url).then(function(response){
                    //把返回的集合赋给这个json对象
                    vue.beans = response.data;
                })
            },
            getUser:function(){
                var url = "users";
                axios.get(url).then(function(response){
                    vue.user = response.data;
                })
            },
            deleteUser:function(){
                var url = "users";
                axios.delete(url).then(function(response){
                    location.href = "admin";
                })
            }
        }
    });
})