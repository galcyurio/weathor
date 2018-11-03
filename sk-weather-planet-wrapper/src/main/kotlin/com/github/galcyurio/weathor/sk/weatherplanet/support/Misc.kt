package com.github.galcyurio.weathor.sk.weatherplanet.support

import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.core.TreeNode

inline fun <reified T : Any> ObjectCodec.treeToValue(n: TreeNode): T = treeToValue(n, T::class.java)