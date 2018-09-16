<!-- In Nuxt to define a dynamic route you must put an underscore before the name of the file -->
<!-- The _id route is dynamic. The id will match the artist name and will be passed into the route -->

<template>
  <div>
    <h1>Results for {{$route.params.id}}</h1>
    <div v-if="albumData">
      <div v-for="(album, index) in albumData">
        <Card :title="album.collectionCensoredName"
              :image="album.artworkUrl60"
              :artistName="album.artistName"
              :url="album.artistViewUrl"
              :color="picker(index)"/>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import Card from '~/components/Card.vue'

  export default {
    asyncData({params}) {
      return axios.get(`https://itunes.apple.com/search?term=${params.id}&entity=album`)
        .then((response) => {
          console.log(response.data.results);
          return {albumData: response.data.results}
        });
    },
    components: {
      Card
    },
    methods: {
      picker(index) {
        return index % 2 == 0 ? 'red' : 'blue'
      }
    },
    computed: {
/*      albumData() {
        return this.$store.state.albums;
      }*/
    },
    middleware: 'search'
  }
</script>
