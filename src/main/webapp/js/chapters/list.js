$(function(){
    var pid = getUrlParms("pid");

    var data4Vue = {
        uri:'chapteries',
        bena:{id:0,name:'',product:{id:0}},
        benas:[],
        product:'',
        category:'',
        kind:'',
        pagination:'',
        user:{id:0,name:'',fileImage:{id:0}}
    }

    var vue = new Vue({
        el:'#category',
        data:data4Vue,
        mounted:function(){
            this.getProduct(pid);
            this.list(0);
            this.getUser();
        },
        methods:{
            getProduct:function(pid){
                var url = "products/" + pid;
                axios.get(url).then(function(response){
                    vue.product = response.data;
                    vue.category = vue.product.category;
                    vue.kind = vue.category.kind;
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

            list:function(story){
                var url = "products/" + pid + "/" + this.uri + "?stary=" + story;
                axios.get(url).then(function(response){
                    vue.benas = response.data.content;
                    vue.pagination = response.data;
                })
            },

            add:function(){
                if(!checkEmpty(this.bena.name, "章数分类"))
                    return;
                var url = this.uri;
                this.bena.product.id = this.product.id;
                axios.post(url, this.bena).then(function(response){+

                    vue.list(0);
                    vue.bena = {id:0,name:'',product:{id:0}};
                });
            },

            deleteBena:function(id){
                if(!checkDeleteLink())
                    return;
                var url = this.uri + "/" + id;
                axios.delete(url).then(function(response){
                    if(response.data.length != 0)
                        alert(response.data);
                    else
                        vue.list(0);
                })
            },

            jump:function(page){
                //跳转首页、尾页、上一页、下一页
                jump(page,vue);
            },
            jumpByNumber: function(start){
                //跳转中间的部分
                jumpByNumber(start,vue);
            },
        }
    });
});