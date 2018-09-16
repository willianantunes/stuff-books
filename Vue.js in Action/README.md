# Vue.js In Action

Coded lessons from the book that you can find [here](https://www.manning.com/books/vue-js-in-action).

All the code is available also at the following: 

- https://github.com/ErikCH/VuejsInActionCode

## Notes

### Chapter 2

- https://vuejs.org/v2/api/#Instance-Methods-Lifecycle

### Chapter 3

- https://dorey.github.io/JavaScript-Equality-Table/

### Chapter 4

- https://vuejs.org/v2/guide/forms.html#v-model-with-Components
- The `v-model` directive is just syntactic sugar. For example `<input v-model="something">` is the same as `<input v-bind:"something" v-on:input="something=$event.target.value">`
- https://vuejs.org/v2/api/%23v-once
- https://vuejs.org/v2/guide/list.html
- https://vuejs.org/v2/guide/forms.html#Modifiers

### Chapter 5

- https://vuejs.org/v2/guide/class-and-style.html
- Promise based HTTP client for the browser and node.js: https://github.com/axios/axios
- https://vuejs.org/v2/guide/list#Mutation-Methods

### Chapter 6

- https://vuejs.org/v2/guide/components.html
- https://vuejs.org/v2/guide/components-registration.html
- https://vuejs.org/v2/guide/components.html#DOM-Template-Parsing-Caveats
- KEBAB-CASE VERSUS CAMELCASE: https://vuejs.org/v2/guide/components.html#camelCase-vs-kebab-case
- https://vuejs.org/v2/guide/components-props.html#Prop-Validation
- Keep in mind, that a single colon by itself, `:`, is shorthand for `v-bind`. Just as the at symbol, `@`, is short hand for `v-on`
- In order to use single file components, Webpack or Browserify is needed to build .vue code.
- The `.sync` modifier is syntactic sugar for this `<my-component :my-counter="counter" @update:my-counter="val => bar = val"></my-component>`

### Chapter 7

- https://vuejs.org/v2/guide/components-slots.html
- https://vuejs.org/v2/guide/components-dynamic-async.html
- https://vuejs-templates.github.io/webpack/static.html
- https://router.vuejs.org/api/#router-link
- https://router.vuejs.org/api/#router-view
- https://router.vuejs.org/guide/advanced/navigation-guards.html
- https://router.vuejs.org/en/advanced/lazy-loading.html