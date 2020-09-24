$(function(){
    var data4Vue = {
        uri:'logins',
        bena:{name:'', password:''},
        isbool:false,
        emptys:''
    }

    var vue = new Vue({
        el:'#login',
        data:data4Vue,
        methods:{
            login:function(){
                if(!userEmpty(this.bena.name, "账号不能为空", vue)){
                    this.isbool = true;
                    return;
                }else if(!userEmpty(this.bena.password, "密码不能为空", vue)){
                    this.isbool = true;
                    return;
                }
                var url = this.uri;
                axios.post(url, this.bena).then(function(response){
                    vue.emptys = response.data;
                    if(response.data.length == 0) {
                        vue.isbool = false;
                        location.href = "admin_kind_list";
                    }else
                        vue.isbool = true;
                })
            }
        }
    })
})