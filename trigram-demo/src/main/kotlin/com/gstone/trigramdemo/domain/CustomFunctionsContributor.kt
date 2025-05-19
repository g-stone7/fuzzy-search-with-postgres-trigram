package com.gstone.trigramdemo.domain

import org.hibernate.boot.model.FunctionContributions
import org.hibernate.boot.model.FunctionContributor
import org.hibernate.dialect.function.StandardSQLFunction
import org.hibernate.query.ReturnableType
import org.hibernate.sql.ast.SqlAstTranslator
import org.hibernate.sql.ast.spi.SqlAppender
import org.hibernate.sql.ast.tree.SqlAstNode
import org.hibernate.type.BasicTypeReference
import org.hibernate.type.SqlTypes
import org.slf4j.LoggerFactory

class CustomFunctionsContributor : FunctionContributor {
    override fun contributeFunctions(functionContributions: FunctionContributions) {
        val trgmWordSimilaritySQLFunction = TrgmWordSimilaritySQLFunction(TRGM_WORD_SIMILARITY)
        functionContributions.functionRegistry.register(
            TRGM_WORD_SIMILARITY, trgmWordSimilaritySQLFunction)
        LOG.info("Registered custom sql function: $TRGM_WORD_SIMILARITY")
    }

    companion object {
        const val TRGM_WORD_SIMILARITY = "trgm_word_similarity"
        val LOG = LoggerFactory.getLogger(CustomFunctionsContributor::class.java)!!
    }
}

internal class TrgmWordSimilaritySQLFunction(functionName: String?) :
    StandardSQLFunction(functionName, true, RETURN_TYPE) {
    override fun render(
        sqlAppender: SqlAppender,
        arguments: List<SqlAstNode?>,
        returnType: ReturnableType<*>,
        walker: SqlAstTranslator<*>
    ) {
        if (arguments.size != 2) {
            throw IllegalArgumentException("Function '$name' requires exactly 2 arguments")
        }

        sqlAppender.append("(")
        arguments[0]!!.accept(walker)
        sqlAppender.append(" <% ")
        arguments[1]!!.accept(walker)
        sqlAppender.append(")")
    }

    companion object {
        private val RETURN_TYPE =
            BasicTypeReference("boolean", Boolean::class.java, SqlTypes.BOOLEAN)
    }
}
