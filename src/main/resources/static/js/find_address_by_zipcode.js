const URL_API = "https://api.zipaddress.net/";

const vm = new Vue ({
    el: '#fabz',
    data: {
        zipcode: '',
        placeholder: '1234567',
        address: ''
    },
    computed: {
        computedZip: function(){
            return !isNaN(this.zipcode) && this.zipcode.length == 7 ? this.zipcode : this.placeholder
        }
    },
    methods: {
        getAddress: function(z){
            let params = {params:{zipcode: z}};
            axios
                .get(URL_API, params)
                .then(res => {
                    this.address = res.data.code == 200 ? res.data.data.fullAddress : res.data.message;
                });
        }
    },
})