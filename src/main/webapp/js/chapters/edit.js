$(function(){
    var id = getUrlParms("id");

    var data4Vue = {
        uri:'chapteries',
        bena:'',
        product:'',
        user:{id:0,name:'',fileImage:{id:0}}
    }

    var vue = new Vue({
        el:'#edit',
        data:data4Vue,
        mounted:function(){
            this.get(id);
            this.getUser();
        },
        methods:{
            get:function(id){
                var url = this.uri + "/" + id;
                axios.get(url).then(function(response){
                    vue.bena = response.data;
                    vue.product = vue.bena.product;
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
            },

            update:function(){
                var url = this.uri;
                axios.put(url, this.bena).then(function(response){
                    vue.get(id);
                })
            }
        }
    });
})