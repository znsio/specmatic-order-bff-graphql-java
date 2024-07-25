# Get event by date

Request

```graphql
query {
    getEventByDate(date: "2020/12/12") { id, date }
}
```

Response

```json
{
  "id": "100",
  "date": "2020/12/12"
}
```
