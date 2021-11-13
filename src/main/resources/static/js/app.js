// Change the API endpoint if necessary
var api = '/'

var app = new Vue({
    el: '#app',
    data: {
        epigram: null,
        epid: null,
        showAlert: false,
        newepigram: null,
        alertSuccess: false,
        alertDanger: false,
        alertMsg: null
    },
    methods: {
        setAlert (isSuccess, msg) {
            this.resetAlert()
            this.alertSuccess = isSuccess
            this.alertDanger = !isSuccess
            this.alertMsg = msg
            this.showAlert = true
        },
        resetAlert () {
            this.showAlert = false
        },
        save: function (event) {
            this.showAlert = false
            axios.put(api + 'epigram/' + this.epid, 'content=' + this.epigram)
                .then(response => {
                    var res = response.data
                    if (res.status === 200) {
                        this.setAlert(true, 'Successfully modified the epigram.')
                    }
                    else {
                        this.setAlert(false, 'API error')
                    }
                })
        },
        deleteConfirm: function (event) {
            axios.delete(api + 'epigram/' + this.epid)
                .then(response => {
                    window.location.replace("/");
                })
        },
        newConfirm: function (event) {
            axios.post(api + 'epigram', 'content=' + this.newepigram)
                .then(response => {
                    console.log(response)
                    var res = response.data
                    if (res.status === 200) {
                        this.epigram = this.newepigram
                        this.newepigram = null
                        this.epid = res.data.id
                        this.setAlert(true, 'Successfully created a new epigram')
                    }
                    else {
                        this.setAlert(false, 'Failed to create a new epigram')
                    }
                })
        },
        closeAlert: function (event) {
            this.resetAlert()
        }
    },
    mounted() {
        axios.get(api + 'epigram/random')
            .then(response => {
                var res = response.data
                if (res.status === 200) {
                    this.epigram = res.data.content
                    this.epid = res.data.id
                }
            })
            .catch(error => {
                this.setAlert(false, 'API error')
            });
    }
})
