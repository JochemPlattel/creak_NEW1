package creak

import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL30.glBindVertexArray
import org.lwjgl.opengl.GL30.glGenVertexArrays

class VAO() {
    var id = 0
    fun init() {
        id = glGenVertexArrays()
    }

    fun bind() {
        glBindVertexArray(id)
    }
}

class VBO(val bufferUsage: BufferUsage) {
    var id = 0
    fun init(size: Long) {
        id = glGenBuffers()
        bind()
        glBufferData(GL_ARRAY_BUFFER, size, bufferUsage.id)
    }
    fun init(data: FloatArray) {
        id = glGenBuffers()
        bind()
        glBufferData(GL_ARRAY_BUFFER, data, bufferUsage.id)
    }

    fun setData(data: FloatArray, offset: Long = 0L) {
        bind()
        glBufferSubData(GL_ARRAY_BUFFER, offset * Float.SIZE_BYTES, data)
    }

    fun bind() {
        glBindBuffer(GL_ARRAY_BUFFER, id)
    }
}

class EBO(val bufferUsage: BufferUsage) {
    var id = 0
    fun init(size: Long) {
        id = glGenBuffers()
        bind()
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, size, bufferUsage.id)
    }
    fun init(data: IntArray) {
        id = glGenBuffers()
        bind()
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, bufferUsage.id)
    }

    fun setData(data: IntArray, offset: Long = 0L) {
        bind()
        glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, offset * Float.SIZE_BYTES, data)
    }

    fun bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id)
    }
}

fun setAttributePointers(layout: IntArray) {
    var pointer = 0L
    for (i in layout.indices) {
        glVertexAttribPointer(
            i,
            layout[i],
            GL_FLOAT,
            false,
            layout.sum() * Float.SIZE_BYTES,
            pointer * Float.SIZE_BYTES
        )
        glEnableVertexAttribArray(i)
        pointer += layout[i]
    }
}

enum class BufferUsage(val id: Int) {
    STATIC_DRAW(GL_STATIC_DRAW), DYNAMIC_DRAW(GL_DYNAMIC_DRAW), STREAM_DRAW(GL_STREAM_DRAW)
}