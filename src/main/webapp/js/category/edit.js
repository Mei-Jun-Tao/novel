$(function(){
    //获取url上的id
    var id = getUrlParms("id");
    //属性数据
    var data4Vue = {
        uri:'categories',
        bena:'',
        kind:'',
        user:{id:0,name:'',fileImage:{id:0}}
    }

    //创建vue对象
    var vue = new Vue({
        el:'#edit',
        data:data4Vue,
        mounted:function(){
            //调用get方法
            this.get(id);
            this.getUser();
        },
        methods:{
            get:function(id){
                var url = this.uri + "/" + id;
                axios.get(url).then(function(response){
                    vue.bena = response.data;
                    vue.kind = vue.bena.kind;
                });
            },

            update:function(){
                if(!checkEmpty(this.bena.name, "分类名称"))
                    return;
                var url = this.uri;
                axios.put(url, this.bena).then(function(response){
                    vue.get(vue.bena.id);
                });
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