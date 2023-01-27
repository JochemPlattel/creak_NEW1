package creak

import org.lwjgl.opengl.GL20.*

class VertexShader(var source: String = "") {
    var id = 0

    fun create() {
        id = glCreateShader(GL_VERTEX_SHADER)
        glShaderSource(id, source)
    }

    fun compile() {
        glCompileShader(id)
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
            val len = glGetShaderi(id, GL_INFO_LOG_LENGTH)
            throw Exception("VertexShader compilation failed: " + glGetShaderInfoLog(id, len))
        }
    }

    companion object {
        fun loadFromFile(filepath: String) {

        }
    }
}

class FragmentShader(var source: String = "") {
    var id = 0

    fun create() {
        id = glCreateShader(GL_FRAGMENT_SHADER)
        glShaderSource(id, source)
    }

    fun compile() {
        glCompileShader(id)
        if (glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
            val len = glGetShaderi(id, GL_INFO_LOG_LENGTH)
            throw Exception("FragmentShader compilation failed: " + glGetShaderInfoLog(id, len))
        }
    }

    companion object {
        fun loadFromFile(filepath: String) {

        }
    }
}

class ShaderProgram(val vertexShader: VertexShader, val fragmentShader: FragmentShader) {
    var id = 0

    fun create() {
        id = glCreateProgram()
        glAttachShader(id, vertexShader.id)
        glAttachShader(id, fragmentShader.id)
        glLinkProgram(id)
        if (glGetProgrami(id, GL_LINK_STATUS) == GL_FALSE) {
            val len = glGetProgrami(id, GL_INFO_LOG_LENGTH)
            throw Exception(glGetProgramInfoLog(id, len))
        }
        glDeleteShader(vertexShader.id)
        glDeleteShader(fragmentShader.id)
    }
    fun use() {
        glUseProgram(id)
    }
}