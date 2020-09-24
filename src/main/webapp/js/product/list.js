$(function(){
    var cid = getUrlParms("cid");

    var data4Vue = {
        uri:'products',
        beans:[],
        pagination:{},
        bena:{id:0,name:'',brief:'',status:'',category:{id:0},user:{id:0}},
        category:'',
        kind:'',
        user:{id:0,name:'',fileImage:{id:0}}
    }

    var vue = new Vue({
        el:'#category',
        data:data4Vue,
        mounted:function(){
            this.getCategory(cid);
            this.list(0);
            this.getUser();
        },
        methods:{
            getCategory:function(cid){
                var url = "categories/" + cid;
                axios.get(url).then(function(response){
                    vue.category = response.data;
                    vue.kind = vue.category.kind;
                })
            },

            list:function(start){
                var url = "categories/"+ cid +"/"+this.uri + "?stary=" + start;
                axios.get(url).then(function(response){
                    vue.beans = response.data.content;
                    //获取分页对象
                    vue.pagination = response.data;
                })
            },

            add:function(){
                if(!checkEmpty(this.bena.name, "书名"))
                    return;
                if(!checkEmpty(this.bena.brief, "简介"))
                    return;
                if(!checkEmpty(this.bena.status, "书的状态"))
                    return;

                var url = this.uri;
                this.bena.category.id = this.category.id;
                this.bena.user.id = this.user.id;
                axios.post(url, this.bena).then(function(response){
                    vue.list(0);
                    vue.bena = {id:0,name:'',brief:'',status:'',category:{id:0},user:{id:0}};
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

            deleteBena:function(id){
                //确认要删除
                if(!checkDeleteLink())
                    return;
                var url = this.uri + "/" + id;
                axios.delete(url).then(function(response){
                    //返回是否为外键束缚
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
})