FROM node:18 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build -- --configuration=production --project=la-casa-del-miele

FROM nginx:alpine
COPY --from=build /app/dist/la-casa-del-miele /usr/share/nginx/html
EXPOSE 80

