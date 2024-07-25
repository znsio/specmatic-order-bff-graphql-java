package com.component.orders.models
import graphql.language.StringValue
import graphql.schema.Coercing
import graphql.schema.CoercingParseLiteralException
import graphql.schema.CoercingParseValueException
import graphql.schema.CoercingSerializeException
import graphql.schema.GraphQLScalarType
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

val DateScalar: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Date")
    .description("A custom scalar that handles Date values")
    .coercing(object : Coercing<Date, String> {
        private val dateFormat = SimpleDateFormat("yyyy/mm/dd")
        private val formatErrorMessage = "Invalid date format. Expected format - yyyy/mm/dd"

        override fun serialize(dataFetcherResult: Any): String {
            return if (dataFetcherResult is Date) {
                dateFormat.format(dataFetcherResult)
            } else {
                throw CoercingSerializeException("Expected a Date object.")
            }
        }

        override fun parseValue(input: Any): Date {
            return try {
                dateFormat.parse(input.toString()) ?: throw CoercingParseValueException(formatErrorMessage)
            } catch (e: ParseException) {
                throw CoercingParseValueException(formatErrorMessage, e)
            }
        }

        override fun parseLiteral(input: Any): Date {
            val dateString = (input as? StringValue)?.value
                ?: throw CoercingParseLiteralException("Expected a StringValue.")
            return try {
                dateFormat.parse(dateString) ?: throw CoercingParseLiteralException(formatErrorMessage)
            } catch (e: ParseException) {
                throw CoercingParseLiteralException(formatErrorMessage, e)
            }
        }
    }).build()
