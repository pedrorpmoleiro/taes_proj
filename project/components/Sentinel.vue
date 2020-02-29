<template>
    <div class="header">
        <h1 class="title">
            Sentinel
        </h1>
        <h2 class="subtitle">@ Polytechnic of Leiria Smart Campus </h2>
    </div>
</template>

<script>
    export default {
        name: "Sentinel"
    }
</script>

<style scoped>
    .title {
        font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
        'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
        display: inline;
        font-weight: 200;
        font-size: 50px;
        color: white;
        letter-spacing: 1px;
        justify-self: center;
    }
    .header {
        top: 0;
        width: 100%;
        height: 60px;
        border-bottom: 1px solid paleturquoise;
        display: flex;
        justify-content: center;
        text-align: center;
        padding: 0px 0px 0px 0px;
        background: #35495e;
    }
    .subtitle {
        font-weight: 300;
        font-size: 20px;
        color: white;
        word-spacing: 5px;
        padding-bottom: 15px;
    }
</style>