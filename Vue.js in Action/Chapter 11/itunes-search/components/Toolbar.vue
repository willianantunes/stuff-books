<template>
  <v-toolbar dark color="blue">
    <v-toolbar-side-icon></v-toolbar-side-icon>
    <v-toolbar-title class="white--text">
      <!-- In Vue.js, we normally use the route-link component to navigate inside
      the application, but this component doesn’t exist in Nuxt -->
      <nuxt-link class="title" to="/">iTunes Search</nuxt-link>
    </v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn to="/" icon>
      <v-icon>refresh</v-icon>
    </v-btn>
  </v-toolbar>
</template>

<script></script>

<style scoped>
  .title {
    text-decoration: none !important;
  }

  .title:visited {
    color: white;
  }
</style>
