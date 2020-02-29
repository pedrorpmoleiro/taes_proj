<template>
    <div>
        <Sentinel></Sentinel>
        <b-card title="Opciones">
            <nuxt-link to="/" class="btn  btn-primary">Voltar</nuxt-link>
            <b-form-group>
                <b-input-group>
                    <b-input-group-prepend>
                        <b-col sm="3">
                            <label for="selectTime">Time:</label>
                        </b-col>
                        <b-col sm="9">
                            <b-form-select v-model="sTime" @change="onChange" id="selectTime">
                                <template v-slot:first>
                                    <option :value="null" disabled>-- Please select an option --</option>
                                </template>
                                <option value="today">Today</option>
                                <option value="lastWeek">Last Week</option>
                                <option value="lastMonth">Last Month</option>
                                <option value="custom">Custom</option>
                            </b-form-select>
                        </b-col>
                    </b-input-group-prepend>
                    <b-input-group v-if="sTime=='custom'">
                        <b-col sm="1">
                            <label for="start">StarDate:</label>
                        </b-col>
                        <input type="date" id="start" v-model="start" @change="custom"/>
                        <b-col sm="1">
                            <label for="start">EndDate:</label>
                        </b-col>
                        <input type="date" id="end" v-model="end" @change="custom"/>
                        <b-badge v-if="error" disabled="true" variant="danger">{{this.msgE}}</b-badge>
                    </b-input-group>
                    <b-input-group-append>
                    <b-col sm="4">
                        <label for="selectType">Type Value:</label>
                    </b-col>
                    <b-form-select v-model="select" @change="typeValue" id="selectType" :disabled="disabled == 1">
                        <template v-slot:first>
                            <option :value="null" disabled>-- Please select an option --</option>
                        </template>
                        <option value="temp">Temperatura</option>
                        <option value="hum"> Humidade</option>
                        <option value="ambos"> Temperatura e Humidade </option>
                    </b-form-select>
                    </b-input-group-append>
                </b-input-group>
            </b-form-group>
        </b-card>
        <b-card-group deck >
            <b-card title="Estatisticas Graficos" body-class="text-center">
                    <bar-chart v-if="sTime!=null":chart-data="this.chartdataT"></bar-chart>
                <b-button  v-if="sTime!=null" @click="showTable" variant="primary">{{this.msgB}}</b-button>
            </b-card>
            <b-card v-if="isTable" title="Table">
                <b-table :items="dataT" :fields="fields"></b-table>
            </b-card>
        </b-card-group>
    </div>
</template>

<script>
    import Sentinel from "~/components/Sentinel.vue";
    import {StoreDB, FieldValue} from "../plugins/firebase";
    import BarChart from "~/components/BarChart";
    import VSwitch from 'v-switch-case';
    import Vue from 'vue';

    Vue.use(VSwitch)
    export default {
        components: {BarChart, Sentinel},
        data: function () {
            return {
                select: [],
                dataF: [],
                sTime: null,
                chartdataT: {},
                sChange: '',
                days: ['2ºFeira', '3ºFeira', '4ºFeira', '5ºFeira', '6ºFeira', 'Sabado', 'Domingo'],
                labels:[],
                disabled:1,
                isTable:false,
                temp:[],
                hum:[],
                dataT:[],
                fields:[],
                item:[],
                msgB:'ShowTable',
                start:'',
                end:'',
                error:false,
                msgE:''

            }
        },
        methods: {
            typeValue(temp) {
                switch (this.select) {
                    case 'temp':
                        switch (this.sChange) {
                            case "today":
                                //console.log('Entro a Temp')
                                var temp = this.dataF.map(e => {
                                    return parseFloat(e.temp)
                                })
                                this.chartdataT={
                                    labels: this.labels,
                                    datasets: [
                                        {
                                            label: 'Temperatura',
                                            backgroundColor: '#f87979',
                                            data: temp
                                        }
                                    ]
                                };
                                this.temp=temp;
                                break;
                            case "lastWeek":
                                //console.log('Entro a Temp')
                                var temp = this.dataF.map(e => {
                                    return parseFloat(e.temp)
                                })
                                this.chartdataT={
                                    labels: this.labels,
                                    datasets: [
                                        {
                                            label: 'Temperatura',
                                            backgroundColor: '#f87979',
                                            data: temp
                                        }
                                    ]
                                };
                                this.temp=temp;
                                break;
                            case "lastMonth":
                                //console.log('Entro a Temp')
                                var temp = this.dataF.map(e => {
                                    return parseFloat(e.temperature)
                                })
                                this.chartdataT={
                                    labels: this.labels,
                                    datasets: [
                                        {
                                            label: 'Temperatura',
                                            backgroundColor: '#f87979',
                                            data: temp
                                        }
                                    ]
                                };
                                this.temp=temp;
                                break;
                            case "custom":
                                //console.log('Entro a Temp')
                                var temp = this.dataF.map(e => {
                                    return parseFloat(e.temperature)
                                })
                                this.chartdataT={
                                    labels: this.labels,
                                    datasets: [
                                        {
                                            label: 'Temperatura',
                                            backgroundColor: '#f87979',
                                            data: temp
                                        }
                                    ]
                                };
                                this.temp=temp;
                                break;
                        }
                        break;
                    case 'hum':
                    switch (this.sChange) {
                        case "today":
                            //console.log('Entro a Temp')
                            var hum = this.dataF.map(e => {
                                return parseInt(e.hum)
                            })
                            this.chartdataT={
                                labels: this.labels,
                                datasets: [
                                    {
                                        label: 'Humidade',
                                        backgroundColor: '#b5f829',
                                        data: hum
                                    }
                                ]
                            };
                            this.hum=hum;
                            // console.log(this.chartdataT);
                            break;
                        case "lastWeek":
                            //console.log('Entro a Temp')
                            var hum = this.dataF.map(e => {
                                return parseInt(e.hum)
                            })
                            this.chartdataT={
                                labels: this.labels,
                                datasets: [
                                    {
                                        label: 'Humidade',
                                        backgroundColor: '#b5f829',
                                        data: hum
                                    }
                                ]
                            };
                            this.hum=hum;
                            // console.log(this.chartdataT);
                            break;
                        default:
                            this.chartdataT={
                                labels: this.labels,
                                datasets: [
                                    {
                                        label: 'Humidade',
                                        backgroundColor: '#b5f829',
                                        data: this.hum
                                    }
                                ]
                            };
                            // console.log(this.chartdataT);
                            break;

                    }
                        break;
                    default:
                        switch (this.sChange) {
                            case "today":
                                var temp = this.dataF.map(e => {
                                    return parseFloat(e.temp)
                                })
                                var hum = this.dataF.map(e => {
                                    return parseInt(e.hum)
                                })
                                this.chartdataT={
                                    labels: this.labels,
                                    datasets: [
                                        {
                                            label: 'Humidade',
                                            backgroundColor: '#b5f829',
                                            data: hum
                                        }, {
                                            label: 'Temperatura',
                                            backgroundColor: '#f87979',
                                            data: temp
                                        }
                                    ]
                                };
                                this.temp=temp;
                                this.hum=hum;
                                break;
                            case "lastWeek":
                                var temp = this.dataF.map(e => {
                                    return parseFloat(e.temp)
                                })
                                var hum = this.dataF.map(e => {
                                    return parseInt(e.hum)
                                })
                                this.chartdataT={
                                    labels: this.labels,
                                    datasets: [
                                        {
                                            label: 'Humidade',
                                            backgroundColor: '#b5f829',
                                            data: hum
                                        }, {
                                            label: 'Temperatura',
                                            backgroundColor: '#f87979',
                                            data: temp
                                        }
                                    ]
                                };
                                this.temp=temp;
                                this.hum=hum;
                                break;
                            default:
                                var temp = this.dataF.map(e => {
                                    return parseFloat(e.temperature)
                                })
                                this.chartdataT={
                                    labels: this.labels,
                                    datasets: [
                                        {
                                            label: 'Humidade',
                                            backgroundColor: '#b5f829',
                                            data: this.hum
                                        }, {
                                            label: 'Temperatura',
                                            backgroundColor: '#f87979',
                                            data: temp
                                        }
                                    ]
                                };
                                this.temp=temp;
                                break;

                        }
                        break;
                }
                if(this.isTable){
                    this.calculateTable();
                }
            },
            onChange() {
                this.disabled++
                switch (this.sTime) {
                    case 'today':
                        this.today();
                        break;
                    case 'lastWeek':
                        this.lastWeek();
                        break;
                    case 'lastMonth':
                        this.lastMonth();
                        break;
                }
            },
            today() {
                let docRef = StoreDB.collection("estadisticas");
                let locais = docRef.doc('today').get()
                    .then(e => {
                        this.dataF = e.data().locais
                        console.log('Today');
                        this.labels = this.dataF.map(e => {
                            return e.text;
                        })
                       this.sChange = 'today';
                        this.typeValue();
                    })
            },
            lastWeek() {
                let docRef = StoreDB.collection("estadisticas");
                let locais = docRef.doc('lastWeek').get()
                    .then(e => {
                        this.dataF = e.data().time
                        console.log('LastWeek');
                        this.labels = this.dataF.map(e => {
                            return e.text;
                        })
                        this.sChange = 'lastWeek';
                        this.typeValue();
                    })
            },
            lastMonth(){
                var currentDay=new Date();
                var lastDmonth=this.getDayLM(currentDay);
                console.log(lastDmonth);
                this.$axios.get('https://api.meteostat.net/v1/history/daily?station=08535&start='+lastDmonth[0]+'&end='+lastDmonth[1]+'&key=lhNrIItF')
                    .then(e=>{
                        console.log('LastMonth',e.data.data);
                        this.dataF=e.data.data;
                        this.labels=this.dataF.map(e=>{
                            return e.date;
                        })
                        this.hum=this.dataF.map(e=>{
                            e.pressure=this.getRandomInt(25,89);
                            return e.pressure;
                        })
                        this.sChange = 'lastMonth';
                        this.typeValue();
                    })
                    .catch(e=>{
                        console.log('Llamadas sobrepasadas',e)
                    })
            },
            custom(){
            if(this.start==''||this.end==''){
                    this.error=true;
                this.msgE='Data start maior do que Data end'
                    return;
                }else{
                var starD=Date.parse(this.start);
                var endD=Date.parse(this.end);
                 if(starD>=endD){
                     this.error=true;
                     this.msgE='Data start maior do que Data end'
                     return;
                 }else{
                     this.error=false;
                     this.$axios.get('https://api.meteostat.net/v1/history/daily?station=08535&start='+this.start+'&end='+this.end+'&key=lhNrIItF')
                         .then(e=>{
                             console.log('LastMonth',e.data.data);
                             this.dataF=e.data.data;
                             this.labels=this.dataF.map(e=>{
                                 return e.date;
                             })
                             this.hum=this.dataF.map(e=>{
                                 e.pressure=this.getRandomInt(25,89);
                                 return e.pressure;
                             })
                             this.sChange = 'lastMonth';
                             this.typeValue();
                         })
                         .catch(e=>{
                             console.log('Llamadas sobrepasadas',e)
                         })
                 }

                }
            },
            showTable(){
                this.isTable=!this.isTable;
                this.msgB=!this.isTable?'Show Table':'Hidden Table';
                this.calculateTable();
            },
            calculateTable(){
                this.fields=[];
                if(this.sChange=='today'){
                    this.fields=[{key:'time',label:'Lugar'}];
                }else{
                    this.fields=[{key:'time',label:'Time'}];
                }
                if(this.select=='ambos'){
                    this.fields.push({key:'temp',label:'Temperatura',sortable: true,variant: 'danger'},{ key: 'hum',label: 'Humidade',sortable: true,variant: 'success'});
                }else{
                    if(this.select=='hum'){
                        this.fields.push({ key: 'hum',label: 'Humidade',sortable: true,variant: 'success'});
                    }else{
                        if(this.select=='temp'){
                            this.fields.push({key:'temp',label:'Temperatura',sortable: true,variant: 'danger'})
                        }
                    }
                }
                console.log(this.fields);
                this.dataT=[];
                var item={}
                for (let i = 0; i < this.labels.length; i++) {
                    var keyA=this.labels[i];
                    var value=this.temp[i];
                    if(this.select=='ambos'){
                        item={time:keyA,temp:value,hum:this.hum[i]}
                    }else{
                        if(this.select=='hum'){
                            item={time:keyA,hum:this.hum[i]}
                        }else{
                            if(this.select=='temp'){
                                item={time:keyA,temp:value}
                            }
                        }
                    }
                    this.dataT.push(item)
                }
            },
            saveData(array) {
                //PERSISTIR
                let docRef = StoreDB.collection("estadisticas").doc('lastMonth');
                let setAda = docRef.set({'time':array})
                    .then(r=>{console.log('Dados persistidos')})
                    .catch(e=>{
                        console.log(e)
                    })
            },
            fillArray(){
                var i=1;
                var lastMonth=[];
                var item={};
                while (i!=31){
                    var temp=this.getRandomInt(12,24);
                    var hum= this.getRandomInt(45,89);
                    item={text:'Day'+i,temp:temp,hum:hum}
                    lastMonth.push(item);
                }
                this.saveData(lastMonth);
            },
            getRandomInt(min, max) {
                min = Math.ceil(min);
                max = Math.floor(max);
                return Math.floor(Math.random() * (max - min + 1)) + min;
            },
            createData(dataObj){
                var y=dataObj.getFullYear();
                var dd = dataObj.getDate();
                var mm = dataObj.getMonth()+1;
                if(dd<10)
                {
                    dd='0'+dd;
                }


                var today= y+'-'+mm+'-'+dd;
                return today;
            },
            getDayLM(today){
                var mm= today.getMonth()+1;
                var dd=today.getDate();
                if(mm<10)
                {
                    mm='0'+mm;
                }
                var mL=mm-1;
                var dd='01';
                return [today.getFullYear()+'-'+mL+'-'+dd,today.getFullYear()+'-'+mm+'-'+dd]
            },
            showData(){
                console.log(this.start)
            }
        },
        mounted() {
            this.sTime='today';
            this.select='temp';
            this.onChange();
            //this.fillArray();
        }
    }
</script>

<style scoped>

</style>