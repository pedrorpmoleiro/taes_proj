<script>
    import { Bar,mixins} from 'vue-chartjs'
    import {StoreDB, FieldValue} from "../plugins/firebase";
    const { reactiveProp } = mixins;
    export default {
        extends: Bar,
        mixins: [reactiveProp],
        props:['op'],
        data:function(){
          return{
              options:{responsive: true, maintainAspectRatio: false}
          }
        },
         mounted () {
            console.log(this.chartData);
             this.renderChart(this.chartData, this.options)
         }
    }
</script>

<style scoped>

</style>