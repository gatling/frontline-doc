# Gatling Enterprise Self-Hosted Documentation

Node.js dependencies:

Setup:

```console
hugo mod get -u 
hugo mod npm pack
npm install
```

Development server:

```console
hugo server -D --debug --baseURL="http://localhost:1313/docs/enterprise/self-hosted/"
```

In case of issue such as `failed to extract shortcode: template for shortcode "img" not found`, run `hugo mod clean`.
